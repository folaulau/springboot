package com.lovemesomecoding.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.dao.UserDAO;
import com.lovemesomecoding.dto.SessionDTO;
import com.lovemesomecoding.dto.SignupRequest;
import com.lovemesomecoding.dto.UserMapper;
import com.lovemesomecoding.jwt.JwtPayload;
import com.lovemesomecoding.jwt.JwtTokenUtils;
import com.lovemesomecoding.model.Role;
import com.lovemesomecoding.model.User;
import com.lovemesomecoding.utils.HttpUtils;
import com.lovemesomecoding.utils.ObjectUtils;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User create(User user) {
		return userDAO.saveAndFlush(user);
	}
	
	@Override
	public User update(User user) {
		return userDAO.update(user);
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub

		return userDAO.getById(id);
	}

	@Override
	public User delete(User user) {
		return userDAO.saveAndFlush(user);
	}
	
}
