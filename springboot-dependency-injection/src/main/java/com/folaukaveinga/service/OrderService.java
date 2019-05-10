package com.folaukaveinga.service;

import com.folaukaveinga.model.User;
import com.folaukaveinga.order.Order;

public interface OrderService {

	Order create(User user);
}
