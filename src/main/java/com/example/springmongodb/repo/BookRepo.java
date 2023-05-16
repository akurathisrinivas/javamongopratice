package com.example.springmongodb.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.springmongodb.model.Book;
import com.mongodb.client.result.UpdateResult;

@Repository
public class BookRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Book save(Book bk) {
		mongoTemplate.save(bk);
		return bk;
	}
	
	
	
	public List<Book> getBookById(String stringId) {
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		return mongoTemplate.find(query, Book.class);
	}
   
	public List<Book> getAllBooks(){
		Query query = new Query();
		List<Book> books = mongoTemplate.find(query,Book.class);
		return books;
	}
	
	public Book updateBookById(String stringId,Book bk){
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		
		//Update updateDefinitaion=new Update().set("bookName",bk.getBookName());
		//FindAndModifyOptions options= new FindAndModifyOptions().returnNew(true).upsert(true);
		
		 Update updateDefinitaion=new Update();
		 updateDefinitaion.set("bookName", bk.getBookName());
		 updateDefinitaion.set("age", bk.getAge());
		 updateDefinitaion.set("authorName", bk.getAuthorName());
		 updateDefinitaion.set("publishedStatus", bk.isPublishedStatus());
		 
		 mongoTemplate.updateMulti(query, updateDefinitaion,Book.class);
		 return bk;
	}
	
	public boolean deleteById(String stringId) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		
		mongoTemplate.remove(query,Book.class);
		return true;
	}
	
	
	


	

}
