package com.example.springmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongodb.model.accountIndex;
import com.example.springmongodb.repo.accountIndexRepo;

@RestController
@RequestMapping("/api/accountIndex")
public class accountIndexController {
	
	
	@Autowired
    private accountIndexRepo repo;
	
	@PostMapping("/add")
    public String saveAccountIndex(@RequestBody accountIndex accountData){
		
		System.out.println(accountData.getAccount_number());
        repo.save(accountData);
        
        return "Added Successfully";
    }
	
	@PostMapping("/updateAccountIndex/{id}")
	public List<accountIndex> updateAI(@PathVariable String id,@RequestBody accountIndex post_accountData) {
		List<accountIndex> accountIndexList = null;
		
	    repo.updateAccountIndexById(id,post_accountData);
	    accountIndexList = repo.getAccountIndexById(id);
		return accountIndexList;
	}
	
	@GetMapping("/getAccountIndex/{id}")
	public accountIndex getAccountIndexById(@PathVariable String id) {
	
		List<accountIndex> accountIndexList = null;
		accountIndexList =repo.getAccountIndexById(id);
		return accountIndexList.get(0);
		
	}
	
	@DeleteMapping("/deleteAccountIndex/{id}")
    public String deleteAccountIndex(@PathVariable String id){
        repo.deleteAccountIndexById(id);
        
        return "Deleted Successfully";
    }
	

}
