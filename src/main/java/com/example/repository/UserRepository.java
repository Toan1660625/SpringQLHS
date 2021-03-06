package com.example.repository;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;
import com.example.entity.User;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create UserRepository class get Data from Table user
 * @version 1.0
 * @author ToanLM
 */

@Repository
public interface UserRepository extends CrudRepository<User, Serializable>{

	@Query(value = "SELECT user_id, user_name, password, role FROM user WHERE user.user_name = ?1",nativeQuery = true)
	Optional<User> findByUserNameNew(String userName);
	
	User findByUserName(String studentCode);
	
}
