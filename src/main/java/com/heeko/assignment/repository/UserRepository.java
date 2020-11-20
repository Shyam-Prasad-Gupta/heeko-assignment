package com.heeko.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heeko.assignment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String emailId);

	@Query(value = "SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
	User isUserExist(String email, String password);

}
