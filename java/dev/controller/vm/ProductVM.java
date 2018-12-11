package dev.controller.vm;

import dev.domain.Product;
import dev.domain.ProductType;

public class ProductVM {
	private String name;
	private String photo;
	private String description;
	private double price;
	private ProductType category;
	private boolean state;
	private int quantity;

	public ProductVM(final Product product) {
		name = product.getName();
		photo = product.getPhoto();
		description = product.getDescription();
		price = product.getPrice();
		category = product.getCategory();
		state = product.isState();
		quantity = product.getQuantity();
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
