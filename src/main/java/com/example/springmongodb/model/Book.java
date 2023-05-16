package com.example.springmongodb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")

public class Book {
	
	    @Id private ObjectId _id;
	    private String bookName;
	    private String authorName;
	    private int age;
	    private boolean publishedStatus;
	    
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public boolean isPublishedStatus() {
			return publishedStatus;
		}
		public void setPublishedStatus(boolean publishedStatus) {
			this.publishedStatus = publishedStatus;
		}

}
