package com.example.springmongodb.model;


import java.util.ArrayList;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.springmongodb.enums.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Employees")
@Getter
@Setter
public class Employee {
	
	@Id private ObjectId _id;
	private String name;
	private double mobile;
	private String gender;
	private ArrayList location;
	private ArrayList profession;
	
	@DBRef Book ObjectId;
	private ObjectId bookId;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt = new Date();
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedAt = new Date();
	
	private Status empStatus ;
	
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public double getMobile() {
		return mobile;
	}
	public void setMobile(double mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ArrayList getLocation() {
		return location;
	}
	public void setLocation(ArrayList location) {
		this.location = location;
	}
	public ArrayList getProfession() {
		return profession;
	}
	public void setProfession(ArrayList profession) {
		this.profession = profession;
	}
	public ObjectId getBookId() {
		return bookId;
	}
	public void setBookId(ObjectId bookId) {
		this.bookId = bookId;
	}
	public Status getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Status empStatus) {
		this.empStatus = empStatus;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/*
	 * public boolean isEmpStatus() { return empStatus; } public void
	 * setEmpStatus(boolean empStatus) { this.empStatus = empStatus; }
	 */
	
}
