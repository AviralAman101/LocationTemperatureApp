package com.etraveli.dao.impl;

import com.etraveli.dao.UserDao;
import com.etraveli.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("select id, first_name, last_name from t_user",
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getLong("id"));
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						return user;
					}
				});
	}

	@Override
	public User findById(Long id) {
		RowMapper<User> rowMapper = new RowMapper<>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(id);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				return user;
			}
		};
		return jdbcTemplate.queryForObject("select first_name, last_name from t_user where id = ?",
				rowMapper, id);
	}

	@Override
	public void create(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement stmt = con.prepareStatement("insert into t_user(id,first_name,last_name) values(user_sequence.nextval,?,?)");
				stmt.setString(1, user.getFirstName());
				stmt.setString(2, user.getLastName());
				return stmt;
			}
		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(User user) {
		jdbcTemplate.update("update t_user set first_name = ?, last_name = ? where id = ?", user.getFirstName(),
				user.getLastName(), user.getId());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from t_user where id = ?", id);
		jdbcTemplate.update("delete from T_PREFERENCE where user_id = ?", id);
	}

}