package com.folaukaveinga.order;

import java.util.ArrayList;
import java.util.List;

import com.folaukaveinga.model.User;
import com.folaukaveinga.product.Product;

public class Order {

	private Long id;
	
	private Double total;
	
	private List<Product> products;
	
	private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if(this.products == null ){
			this.products = new ArrayList<>();
		}
		this.products.add(product);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
