package com.example.springmongodb.controller;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongodb.model.Book;

import com.example.springmongodb.repo.BookRepo;


@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
    private BookRepo repo;
	
	@PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        repo.save(book);
        
        return "Added Successfully";
    }
	
	@GetMapping("/getBook/{id}")
	public List<Book> getBookById(@PathVariable String id) {
	
		List<Book> bookList = null;
		bookList = repo.getBookById(id);
		return bookList;
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
	    List<Book> bookList = null;
		bookList = repo.getAllBooks();
		return bookList;
	}
	
	@PostMapping("/updateBook/{id}")
	public List<Book> updateBook(@PathVariable String id,@RequestBody Book book) {
	    List<Book> bookList = null;
	    repo.updateBookById(id,book);
	    bookList = repo.getBookById(id);
		return bookList;
	}
	
	@DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        repo.deleteById(id);
        
        return "Deleted Successfully";
    }
	
	
}
