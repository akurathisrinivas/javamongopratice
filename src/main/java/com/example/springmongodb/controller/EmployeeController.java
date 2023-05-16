package com.example.springmongodb.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongodb.model.Book;
import com.example.springmongodb.model.Employee;
import com.example.springmongodb.repo.EmployeeRepo;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
    private EmployeeRepo repo;
	
	@PostMapping("/add")
    public String saveEmployee(@RequestBody Employee emp){
        repo.save(emp);
        
        return "Added Successfully";
    }
	
	@GetMapping("/getEmployee/{id}")
	public Document getEmployeeById(@PathVariable String id) {
	
		
		return repo.getEmployeeById(id);
		
	}
	
	@GetMapping("/getAllEmployees")
	public Document getAllEmployees() {
	
		
		return repo.getAllEmployees();
		
	}
	
	@PostMapping("/updateEmployee/{id}")
	public Document updateEmployee(@PathVariable String id,@RequestBody Employee emp) {
	    Document employeeList = null;
	    repo.updateEmployeeId(id,emp);
	    employeeList = repo.getEmployeeById(id);
		return employeeList;
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable String id){
        repo.deleteById(id);
        
        return "Deleted Successfully";
    }
}
