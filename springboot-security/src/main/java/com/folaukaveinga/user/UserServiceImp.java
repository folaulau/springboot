package com.folaukaveinga.user;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.dto.SessionDTO;
import com.folaukaveinga.dto.SignupRequest;
import com.folaukaveinga.dto.UserDto;
import com.folaukaveinga.dto.UserFullDto;
import com.folaukaveinga.dto.UserMapper;
import com.folaukaveinga.jwt.JwtPayload;
import com.folaukaveinga.jwt.JwtTokenUtils;
import com.folaukaveinga.role.Role;
import com.folaukaveinga.utils.HttpUtils;
import com.folaukaveinga.utils.ObjectUtils;
import com.folaukaveinga.utils.PasswordUtils;
import com.folaukaveinga.utils.RandomGeneratorUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserMapper userMapper;

	@Override
	public User create(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub

		return userRepository.getById(id);
	}

	@Override
	public Optional<User> findByUid(String uuid) {
		// TODO Auto-generated method stub
		return userRepository.findByUid(uuid);
	}

	@Override
	public User getByUid(String uid) {
		// TODO Auto-generated method stub
		return userRepository.getByUid(uid);
	}

	@Override
	public User getProfileById(Long id) {

		User user = userRepository.getById(id);

		log.info("user: {}", ObjectUtils.toJson(user));

		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userRepository.getByEmail(email);
	}

	@Override
	public SessionDTO signUp(SignupRequest signupRequest) {
		log.debug("signup(..)");
		User user = this.userMapper.signupRequestToUser(signupRequest);

		Role role = new Role();
		role.setAuthority(Role.USER);
		role.addUser(user);
		user.addRole(role);

		role = new Role();
		role.setAuthority(Role.MANAGER);
		role.addUser(user);
		user.addRole(role);

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		user.setUid(RandomGeneratorUtils.getUuid());
		log.debug("user: {}", ObjectUtils.toJson(user));

		user = this.create(user);

		log.debug("saved user: {}", ObjectUtils.toJson(user));
		JwtPayload jwtpayload = new JwtPayload(user, RandomGeneratorUtils.getUuid());

		String clientUserAgent = HttpUtils.getRequestUserAgent(request);

		jwtpayload.setDeviceId(clientUserAgent);
		
		log.debug("jwtpayload: {}", ObjectUtils.toJson(jwtpayload));
		
		String jwtToken = JwtTokenUtils.generateToken(jwtpayload);

		SessionDTO ssnDto = new SessionDTO();
		ssnDto.setEmail(user.getEmail());
		ssnDto.setName(user.getName());
		ssnDto.setUserUid(user.getUid());
		ssnDto.setToken(jwtToken);
		
		return ssnDto;
	}
}
