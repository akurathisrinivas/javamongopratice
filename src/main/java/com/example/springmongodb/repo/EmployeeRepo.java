package com.example.springmongodb.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.springmongodb.model.Book;
import com.example.springmongodb.model.Employee;



@Repository
public class EmployeeRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Employee save(Employee emp) {
		mongoTemplate.save(emp);
		return emp;
	}
	
	public Document getEmployeeById(String stringId) {
		
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		//query.addCriteria(Criteria.where("_id").is(objectId));
		
		LookupOperation lookupOperation = LookupOperation.newLookup().
	            from("Books").
	            localField("bookId").
	            foreignField("_id").
	            as("book_info");
		
		//UnwindOperation unwindOperation = Aggregation.unwind("$book_info");
		
		ProjectionOperation projectionOperation = Aggregation.project("_id","name","mobile","gender","location","profession","book_info.bookName").and("_id").previousOperation();
				 
		
		MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(objectId));
		
		Aggregation aggregation = Aggregation.newAggregation(matchOperation,lookupOperation,projectionOperation);
		
		AggregationResults<Employee> response=mongoTemplate.aggregate(aggregation, "Employees", Employee.class);
	    
		return response.getRawResults();
		//return mongoTemplate.find(query, Employee.class);
	}
	
	public Document getAllEmployees() {
		
		
		LookupOperation lookupOperation = LookupOperation.newLookup().
	            from("Books").
	            localField("bookId").
	            foreignField("_id").
	            as("book_info");
		
		ProjectionOperation projectionOperation = Aggregation.project("_id","name","mobile","gender","location","profession","book_info.bookName").and("_id").previousOperation();
		
		Aggregation aggregation = Aggregation.newAggregation(lookupOperation,projectionOperation);
		
		AggregationResults<Employee> response=mongoTemplate.aggregate(aggregation, "Employees", Employee.class);
	    
		return response.getRawResults();
	}
	
	public Employee updateEmployeeId(String id,Employee emp) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(id);
		query.addCriteria(Criteria.where("_id").is(objectId));
		
		//Update updateDefinitaion=new Update().set("bookName",bk.getBookName());
		//FindAndModifyOptions options= new FindAndModifyOptions().returnNew(true).upsert(true);
		
		 Update updateDefinitaion=new Update();
		 updateDefinitaion.set("name", emp.getname());
		 updateDefinitaion.set("mobile", emp.getMobile());
		 updateDefinitaion.set("gender", emp.getGender());
		 updateDefinitaion.set("location", emp.getLocation());
		 updateDefinitaion.set("profession", emp.getProfession());
		 updateDefinitaion.set("bookId", emp.getBookId());
		 updateDefinitaion.set("empStatus", emp.getEmpStatus());
		 updateDefinitaion.set("updatedAt", emp.getUpdatedAt());
		 
		 mongoTemplate.updateMulti(query, updateDefinitaion,Employee.class);
		 return emp;
	}
	
	public boolean deleteById(String stringId) {
		
		Query query = new Query();
		ObjectId objectId = new ObjectId(stringId);
		query.addCriteria(Criteria.where("_id").is(objectId));
		
		mongoTemplate.remove(query,Employee.class);
		return true;
	}
}
