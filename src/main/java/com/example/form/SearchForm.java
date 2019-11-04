package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create SearchForm  class
 * @version 1.0
 * @author ToanLM
 */

public class SearchForm {
	
	@NotEmpty(message = "Student name must be not null for search!")
	private String studentName;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
