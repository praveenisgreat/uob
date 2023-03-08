package com.ms.core.auth.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.core.auth.exception.AppException;
import com.ms.core.auth.model.AbilityAction;
import com.ms.core.auth.model.AbilityModel;
import com.ms.core.auth.model.UserModel;
import com.ms.core.auth.repository.AbilityRepository;
import com.ms.core.auth.repository.UserRepository;
import com.ms.core.auth.request.ApiResponse;
import com.ms.core.auth.request.SignupRequest;

@RestController
@RequestMapping("/")
public class AuthController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AbilityRepository abilityRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String home() {
		// For debugging
		return "Hello from Auth Service running at port: " + env.getProperty("local.server.port");
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		// Creating user's account
		UserModel userModel = new UserModel();
		userModel.setFirstName(signUpRequest.getFirstName());
		userModel.setLastName(signUpRequest.getLastName());
		userModel.setUsername(signUpRequest.getUsername());
		userModel.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		userModel.setAvatar(signUpRequest.getAvatar());
		userModel.setEmail(signUpRequest.getEmail());
		userModel.setRole(signUpRequest.getRole());
		
		AbilityModel userAbility = abilityRepository.findByAction(AbilityAction.MANAGE)
				.orElseThrow(() -> new AppException("User Ability not set."));
		userModel.setAbility(Collections.singleton(userAbility));
		
		userRepository.save(userModel);
		return ResponseEntity.status(HttpStatus.OK)
                .body("Employee registered successfully");
		
	}
}
