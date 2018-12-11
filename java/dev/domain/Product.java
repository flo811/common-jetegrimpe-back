package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String photo;
	private String description;
	private double price;
	private ProductType category;
	private boolean state;
	private int quantity;
	
	public Product() {
	}

	public Product(String name, String photo, String description, double price, ProductType category, boolean state, int quantity) {
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.price = price;
		this.category = category;
		this.state = state;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getCategory() {
		return category;
	}

	public void setCategory(ProductType category) {
		this.category = category;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
