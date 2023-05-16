package com.example.springmongodb.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import com.example.springmongodb.model.accountIndex;

@Repository
public class accountIndexRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public accountIndex save(accountIndex accountData) {
		mongoTemplate.save(accountData);
		return accountData;
	}
	
		//Replacing a whole document.
		public accountIndex  updateAccountIndexById(String id,accountIndex post_data) {
			// TODO Auto-generated method stu
			Query query = new Query();
			ObjectId objectId = new ObjectId(id);
			query.addCriteria(Criteria.where("_id").is(objectId));
			
			//Update updateDefinitaion=new Update().set("bookName",bk.getBookName());
			//FindAndModifyOptions options= new FindAndModifyOptions().returnNew(true).upsert(true);
			
			 Update updateDefinitaion=new Update();
			 updateDefinitaion.set("account_number", post_data.getAccount_number());
			 updateDefinitaion.set("account_type", post_data.getAccount_type());
			 updateDefinitaion.set("account_opened_on", post_data.getAccount_opened_on());
			 updateDefinitaion.set("sort_code", post_data.getSort_code());
			 updateDefinitaion.set("account_status", post_data.getAccount_status());
			 
			 mongoTemplate.updateMulti(query, updateDefinitaion,accountIndex.class);
			 return post_data;
		}
		
		public List<accountIndex> getAccountIndexById(String stringId) {
			
			Query query = new Query();
			ObjectId objectId = new ObjectId(stringId);
			query.addCriteria(Criteria.where("_id").is(objectId));
			return  mongoTemplate.find(query, accountIndex.class);
		}
		
		
		public boolean deleteAccountIndexById(String stringId) {
			
			Query query = new Query();
			ObjectId objectId = new ObjectId(stringId);
			query.addCriteria(Criteria.where("_id").is(objectId));
			
			mongoTemplate.remove(query,accountIndex.class);
			return true;
		}
}
