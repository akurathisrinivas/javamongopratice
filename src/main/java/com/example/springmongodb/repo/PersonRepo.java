package com.example.springmongodb.repo;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.springmongodb.model.Book;
import com.example.springmongodb.model.Employee;
import com.example.springmongodb.model.Person;
import com.mongodb.WriteConcernResult;
import com.mongodb.client.result.UpdateResult;
import com.example.springmongodb.dto.GroceryItem;


@Repository
public class PersonRepo {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Person save(Person per) {
		mongoTemplate.save(per);
		return per;
	}
	
	public List<Person> getPersonById(String stringId) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		return  mongoTemplate.find(query, Person.class);
	}
	
	public Document getPerson(String stringId) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		return  (Document) mongoTemplate.find(query, Person.class);
	}
	
	public List<Person> getSpecificValuesOfPersonById(String stringId) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		//query.fields().include("firstName").exclude("lastName");
		query.fields().include("firstName","lastName","dateOfBirth");
		query.addCriteria(Criteria.where("_id").is(objectId));
		//Criteria orCriteria = new Criteria();
		//List<Criteria> inventoryList =  new ArrayList<>();
		List<Person> personList = mongoTemplate.find(query, Person.class);
		
		personList.forEach(i -> {
			   i.getFirstName();
			   i.getLastName();
			   i.getDateOfBirth();
			   
			});
		
		return personList;
	}

		//Replacing a whole document.
	public Person  updatePersonId(String id,Person post_per) {
		// TODO Auto-generated method stu
		Query query = new Query();
		ObjectId objectId = new ObjectId(id);
		query.addCriteria(Criteria.where("_id").is(objectId));
		
		//Update updateDefinitaion=new Update().set("bookName",bk.getBookName());
		//FindAndModifyOptions options= new FindAndModifyOptions().returnNew(true).upsert(true);
		
		 Update updateDefinitaion=new Update();
		 updateDefinitaion.set("firstName", post_per.getFirstName());
		 updateDefinitaion.set("lastName", post_per.getLastName());
		 updateDefinitaion.set("dateOfBirth", post_per.getDateOfBirth());
		 updateDefinitaion.set("profession", post_per.getProfession());
		 updateDefinitaion.set("salary", post_per.getSalary());
		 updateDefinitaion.set("hobbies", post_per.getHobbies());
		 updateDefinitaion.set("groceryitems", post_per.getGroceryitems());
		 mongoTemplate.updateMulti(query, updateDefinitaion,Person.class);
		 return post_per;
	}

	//Update Nested object with in the array of document.
	public UpdateResult updateGroceryItemById(String id,String itemId,GroceryItem post_per) {
	
		ObjectId groceryObjectId = new ObjectId(itemId);
		ObjectId objectId = new ObjectId(id);
		//System.out.println(objectId);
		//System.out.println(groceryObjectId);
		final Query query = new Query(new Criteria().andOperator(
		        Criteria.where("_id").is(objectId),
		       Criteria.where("groceryitems").elemMatch(Criteria.where("_id").is(groceryObjectId))
		));
		//System.out.println(query);
		 Update update = new Update().set("groceryitems.$.quantity", post_per.getQuantity());
		 update.set("groceryitems.$.name", post_per.getName());
		 update.set("groceryitems.$.category", post_per.getCategory());
		 
		 //System.out.println(update);
		 UpdateResult wr = mongoTemplate.updateFirst(query, update, Person.class);
		 		
		 return wr;
	}
	
		//Add Item Nested object with in the array of document.
		public UpdateResult addGroceryItemById(String id,GroceryItem post_per) {
		
			ObjectId objectId = new ObjectId(id);
			//System.out.println(objectId);
			//System.out.println(groceryObjectId);
			final Query query = new Query(new Criteria().andOperator(
			        Criteria.where("_id").is(objectId)
			 ));
			//System.out.println(query);
			 
			 Update update = new Update().push("groceryitems", post_per);
			 //System.out.println(update);
			 UpdateResult wr = mongoTemplate.updateFirst(query, update, Person.class);
			 		
			 return wr;
		}
		
		
		//delete Nested object with in the array of document.
		public UpdateResult deleteGroceryItemById(String id,String itemId,GroceryItem post_per) {
		
			ObjectId groceryObjectId = new ObjectId(itemId);
			ObjectId objectId = new ObjectId(id);
			//System.out.println(objectId);
			//System.out.println(groceryObjectId);
			final Query query = new Query(new Criteria().andOperator(
			        Criteria.where("_id").is(objectId)
			        //Criteria.where("groceryitems").elemMatch(Criteria.where("_id").is(groceryObjectId))
			));
			
			 //System.out.println(query);
			 //Update update = new Update();
			 
			 //update.pull("groceryitems.$._id",groceryObjectId);
			 
			 //System.out.println(update);
			// UpdateResult wr = mongoTemplate.updateMulti(query, update, Person.class);
			 
			UpdateResult wr= this.mongoTemplate.updateMulti(query,
				        new Update().pull("groceryitems", Query.query(Criteria.where("_id").is(groceryObjectId))), Person.class);
			 
			 //System.out.println(wr.getUpsertedId());
			 return wr;
			 
		}
		
}
