package com.example.form;

import javax.validation.constraints.NotEmpty;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create LoginForm class
 * @version 1.0
 * @author ToanLM
 */
public class LoginForm {

	@NotEmpty(message = "User Name must be not null!")
	private String userName;

	@NotEmpty(message = "Password must be not null!")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
