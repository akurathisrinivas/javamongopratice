package com.example.springmongodb.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("groceryitems")
@Data
public class GroceryItem {

	
	@Id private String _id;
	//@Id private ObjectId _id;

    private String name;
    private int quantity;
    private String category;
    
    public GroceryItem(String name, int quantity, String category) {
        //super();
        this.set_id(_id);
        this.setName(name);
        this.setQuantity(quantity);
        this.setCategory(category);
    }
    
    public void set_id(String _id) {
		this._id = new ObjectId().toString();
	}
    public String get_id() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
