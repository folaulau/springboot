package com.folaukaveinga.testing.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp implements UserDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailById(long id) {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT email ");

		queryBuilder.append("FROM user ");
		queryBuilder.append("WHERE id = :id ");
		try {
			Query query = this.entityManager.createNativeQuery(queryBuilder.toString());

			query.setParameter("id", id);

			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			log.info("NoResultException={}", e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Long getIdByEmail(String email) {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT id ");

		queryBuilder.append("FROM user ");
		queryBuilder.append("WHERE email = ? ");
		try {
			return jdbcTemplate.queryForObject(queryBuilder.toString(), new Object[] { email }, Long.class);
		} catch (DataAccessException e) {
			log.warn("DataAccessException={}", e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean deleteById(long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
		return true;
	}

	@Override
	public List<User> getByLastName(String lastName) {
		// TODO Auto-generated method stub
		try {
			return userRepository.findByLastName(lastName).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
