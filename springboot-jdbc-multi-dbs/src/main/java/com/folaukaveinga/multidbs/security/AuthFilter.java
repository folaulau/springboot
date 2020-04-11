package com.folaukaveinga.multidbs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("AuthFilter.doFilterInternal(...)");

		String url = request.getRequestURL().toString();

		if (request.getSession() != null) {
			log.info("session id={}", request.getSession().getId());
		}

		log.info("url={}", url);

		filterChain.doFilter(request, response);

		log.info("done filter!");

		// invalidate session after each http call.(no use case for this)
		// request.getSession().invalidate();

	}

}
