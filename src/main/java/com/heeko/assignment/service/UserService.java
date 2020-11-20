package com.heeko.assignment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heeko.assignment.dao.UserDAO;
import com.heeko.assignment.entity.User;
import com.heeko.assignment.model.AuthDataJson;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public User createUserAccount(final AuthDataJson body) {
		User user = new User(body.getEmail(), body.getPassword());
		return userDAO.saveUser(user);
	}

	public boolean isUserExistByEmail(final String email) {
		return userDAO.isUserExistByEmail(email);
	}

	public boolean isUserExistByEmailAndPassword(final AuthDataJson user) {
		return userDAO.isUserExistByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public String giveMeToken() {
		return "Token:" + UUID.randomUUID().toString() + System.currentTimeMillis();
	}

}
