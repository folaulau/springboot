package com.folaukaveinga.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.folaukaveinga.model.User;
import com.folaukaveinga.order.Order;
import com.folaukaveinga.service.OrderService;
import com.folaukaveinga.service.UserService;
import com.folaukaveinga.utils.ObjectUtils;

@Component
public class UserLoader {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@PostConstruct
	public void init() {
		log.info("init()");
		User user = new User();
		Order order  = orderService.create(user);
		log.info("order={}",ObjectUtils.toJson(order));
	}
}
