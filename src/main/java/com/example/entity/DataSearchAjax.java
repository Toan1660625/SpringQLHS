package com.example.entity;

import java.util.List;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  DataSearchAjax class
 * @version 1.0
 * @author ToanLM
 */
public class DataSearchAjax {

	String message;
	List<Student> result;
	int totalPage;
	int totalStudent;

	public List<Student> getResult() {
		return result;
	}

	public void setResult(List<Student> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

}
