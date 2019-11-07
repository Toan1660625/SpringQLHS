package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.User;
import com.example.repository.UserRepository;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  UserService class
 * @version 1.0
 * @author ToanLM
 */

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// Find User by ID
	public User getUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();

	}
	// Find User by Email
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByUserNameNew(email);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}

	}

	// Find User by name
	public User findByUserName(String email) {
		User user = userRepository.findByUserName(email);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	// Save user have using Rollback when error
	@Transactional(rollbackFor = { Exception.class })
	public void save(User entity) {
		try {
			userRepository.save(entity);
		} catch (Exception e) {
			System.out.println("Lỗi Save User ");
		}
	}

	//Update user
	public User createOrUpdateUser(User entity) {
		Optional<User> user = userRepository.findById(entity.getUserId());

		if (user.isPresent()) {
			User newEntity = user.get();
			newEntity.setUserId(entity.getUserId());
			newEntity.setUserName(entity.getUserName());
			newEntity.setPassword(entity.getPassword());

			newEntity = userRepository.save(newEntity);

			return newEntity;
		} else {
			entity = userRepository.save(entity);
			return entity;
		}
	}

	//Delete user by ID
	public void deleteEmployeeById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		userRepository.deleteById(id);

	}
}
