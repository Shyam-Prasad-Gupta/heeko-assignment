package com.heeko.assignment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heeko.assignment.model.AuthDataJson;
import com.heeko.assignment.service.UserService;

@RestController
@RequestMapping(value = "/heeko/api/")
public class SignInSignUpController {

	Logger log = LoggerFactory.getLogger(SignInSignUpController.class);

	@Autowired
	private UserService userService;

	@CrossOrigin
	@PostMapping(value = "signin")
	public String signIn(@RequestBody final AuthDataJson body, final HttpServletRequest request,
			final HttpServletResponse response) {
		System.out.println("here in signin");
		String message = null;
		try {
			boolean isExist = userService.isUserExistByEmail(body.getEmail());
			if (!isExist) {
				userService.createUserAccount(body);
				response.setStatus(HttpStatus.OK.value());
				message = userService.giveMeToken();// "User is registered successfully!";
			} else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				message = "User already exist please login!";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			message = "Something went wrong! Please try again.";
		}
		return message;
	}

	@CrossOrigin
	@PostMapping(value = "login")
	public String login(@RequestBody final AuthDataJson body, final HttpServletRequest request,
			final HttpServletResponse response) {
		System.out.println("here in login");
		String message = null;
		try {
			if (userService.isUserExistByEmailAndPassword(body)) {
				message = userService.giveMeToken();
				response.setStatus(HttpStatus.OK.value());
			} else {
				message = "User doesn't exist! Please register!";
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Error occurred while logging in the user with email={}", body.getEmail());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			message = "Something went wrong! Please try again.";
		}
		return message;
	}

	@CrossOrigin
	@GetMapping(value = "health-checkup")
	public String healthCheckup(final HttpServletRequest request, final HttpServletResponse response) {
		System.out.println("Here in checkup");
		return "Everything is fine.";
	}

}
