package com.example.springmongodb.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.springmongodb.model.Person;
import com.example.springmongodb.repo.PersonRepo;
import com.example.springmongodb.dto.GroceryItem;


@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
    private PersonRepo repo;
	
	@PostMapping("/add")
    public String savePerson(@RequestBody Person per){
        repo.save(per);
        
        return "Added Successfully";
    }
	
	@GetMapping("/getPerson/{id}")
	public Person getPersonById(@PathVariable String id) {
	
		List<Person> personList = null;
		personList =repo.getPersonById(id);
		return personList.get(0);
		
	}
	
	@GetMapping("/getSpecificValuesOfPerson/{id}")
	public List<Person> getSpecificValuesOfPersonById(@PathVariable String id) {
	
		List<Person> personList = null;
		personList =repo.getSpecificValuesOfPersonById(id);
		return personList;
		
	}
	//Replacing a whole document.
	@PostMapping("/updatePerson/{id}")
	public List<Person> updatePerson(@PathVariable String id,@RequestBody Person post_per) {
		List<Person> personList = null;
		
	    repo.updatePersonId(id,post_per);
	    personList = repo.getPersonById(id);
		return personList;
	}
	
	//Update Nested object with in the array of document.
	@PostMapping("/updateGroceryItem/{id}/{itemId}")
	public List<Person> updateGroceryItem(@PathVariable String id,@PathVariable String itemId,@RequestBody GroceryItem gro_item) {
		List<Person> personList = null;
		
	    repo.updateGroceryItemById(id,itemId,gro_item);
		//System.out.println(gro_item);
	    personList = repo.getPersonById(id);
		return personList;
	}
	
	   //Add Nested object with in the array of document.
		@PostMapping("/addGroceryItem/{id}")
		public List<Person> addGroceryItem(@PathVariable String id,@RequestBody GroceryItem gro_item) {
			List<Person> personList = null;
			
		    repo.addGroceryItemById(id,gro_item);
			//System.out.println(gro_item);
		    personList = repo.getPersonById(id);
			return personList;
		}
	
		//Delete Nested object with in the array of document.
		@PostMapping("/deleteGroceryItem/{id}/{itemId}")
		public List<Person> deleteGroceryItem(@PathVariable String id,@PathVariable String itemId,@RequestBody GroceryItem gro_item) {
			List<Person> personList = null;
			
		    repo.deleteGroceryItemById(id,itemId,gro_item);
			//System.out.println(gro_item);
		    personList = repo.getPersonById(id);
			return personList;
		}

}
