package com.kavi.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kavi.user.dao.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> findByUserName(String userName) {
		StringBuffer query = new StringBuffer(50);
		List<Map<String, Object>> listOfUsers = null;
		try {
			query.append("select ");
			query.append(" * ");
			query.append(" from ");
			query.append(" app_users ");
			query.append(" where ");
			query.append(" upper(username) = ");
			query.append(" upper(?) ");

			listOfUsers = jdbcTemplate.queryForList(query.toString(), new Object[]{userName});

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listOfUsers;
	}

}
