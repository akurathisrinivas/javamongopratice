package com.example.springmongodb.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ACCOUNT_INDEX")
@Getter
public class accountIndex {

	@Id 
	private ObjectId _id;
	@NotNull(message = "Account number is missing")
	@NotBlank(message = "The country is required.")
	@Size(max = 8)
	@Size(min = 8)
	private String account_number;
	
	private LocalDateTime   account_opened_on;
	private String account_type;
	private String sort_code;
	private String account_status;
	
	
	
	
	
	
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public LocalDateTime getAccount_opened_on() {
		return account_opened_on;
	}
	public void setAccount_opened_on(LocalDateTime account_opened_on) {
		this.account_opened_on = account_opened_on;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getSort_code() {
		return sort_code;
	}
	public void setSort_code(String sort_code) {
		this.sort_code = sort_code;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	
}
