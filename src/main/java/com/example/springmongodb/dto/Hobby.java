package com.example.springmongodb.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class Hobby {

	
	private String name;

	
	  public Hobby(String name) { this.setName(name); }
	  
	  public String getName() { return name; }
	  
	  public void setName(String name) { this.name = name; }
	 
	
	/*
	 * public Hobby( String name) { this.name = name; }
	 */

}
