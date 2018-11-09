package com.folaukaveinga.springboot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.springboot.utility.Factory;

@Component
public class CustomAcccessDeniedHandler implements AccessDeniedHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("access denied");
		ObjectNode msg = Factory.getObjectMapper().createObjectNode();
		msg.put("msg", "denied");
		Factory.getObjectMapper().writeValue(response.getWriter(), msg);
	}

}