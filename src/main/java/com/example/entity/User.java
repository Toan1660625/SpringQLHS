package com.example.entity;
// Generated Oct 14, 2019 10:49:53 AM by Hibernate Tools 5.1.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create User class
 * @version 1.0
 * @author ToanLM
 */

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private int userId;
	private String userName;
	private String password;
	private String role;

	public User() {
		this.userId = 0;
		this.userName = null;
		this.password = null;
	}

	public User(String userName, String password, String role) {
		super();
		this.userId = 0;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "userName", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 15)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role", length = 20)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
