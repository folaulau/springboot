package com.folaukaveinga.service;

import com.folaukaveinga.model.User;
import com.folaukaveinga.order.Order;
import com.folaukaveinga.product.Product;

public class OrderServiceImp implements OrderService {

	@Override
	public Order create(User user) {
		Order order = new Order();
		order.setUser(user);
		order.addProduct(new Product("Hawaiian BQQ Plate",12.50));
		order.addProduct(new Product("Suchi Plate",14.50));
		return order;
	}

}
