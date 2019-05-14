package com.lovemesomecoding.user;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.dto.UserMapper;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User create(User user) {
		StringBuilder query = new StringBuilder("INSERT INTO member(uid, email, password) VALUES (?, ?, ?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getUid());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			return ps;
			
		}, keyHolder);

		long id = keyHolder.getKey().longValue();

		user.setId(id);

		return user;
	}

	@Override
	public User getById(Long id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT id, email, password, uid FROM member WHERE id = ?");
		User user = jdbcTemplate.queryForObject(query.toString(), new UserRowMapper(), id);
		return user;
	}

	@Override
	public User update(User user) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE member SET email = ? , password = ? , uid = ? WHERE id = ?");
		int updated = jdbcTemplate.update(query.toString(), user.getEmail(), user.getPassword(), user.getUid(), user.getId());
		log.info("updated={}", updated);
		return user;
	}

	@Override
	public boolean delete(User user) {
		log.info("delete user id={}",user.getId());
		String sql = "DELETE FROM member WHERE id = ?";
		int deleted = jdbcTemplate.update(sql, user.getId());
		return (deleted>0) ? true:false;
	}

	@Override
	public void setup() {
		// jdbcTemplate.execute("DROP TABLE member IF EXISTS");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS member(id bigint(20) NOT NULL AUTO_INCREMENT, email varchar(255) NOT NULL, password varchar(255) NOT NULL, uid varchar(255) NOT NULL, PRIMARY KEY (`id`))");
	}

	@Override
	public List<User> getAll() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT id, email, password, uid FROM member");
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(query.toString(), rowMapper);
		return users;
	}
}
