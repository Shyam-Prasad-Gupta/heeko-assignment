package com.heeko.assignment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heeko.assignment.entity.User;
import com.heeko.assignment.repository.UserRepository;

@Repository
public class UserDAO {

	@Autowired
	private UserRepository userRepo;

	public boolean isUserExistByEmail(final String emailId) {
		return userRepo.existsByEmail(emailId);
	}

	public User saveUser(final User user) {
		return userRepo.save(user);
	}

	public boolean isUserExistByEmailAndPassword(final String email, final String password) {
		User user = userRepo.isUserExist(email, password);
		if (user != null && user instanceof User) {
			return true;
		}
		return false;
	}

}
