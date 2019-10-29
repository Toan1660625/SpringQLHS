package com.example.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create AddStudentForm class
 * @version 1.0
 * @author ToanLM
 */
public class AddStudentForm {
	
	@NotEmpty(message = "Student id must be not null!")
	private String studentId;
	
	@NotEmpty(message = "Student code must be not null!")
	private String studentCode;

	@NotEmpty(message = "Student name must be not null!")
	private String studentName;

	@NotEmpty(message = "Student address must be not null!")
	private String address;
	
	@Max(value = 10, message = "Score must be less than 10!")
	@Min(value = 1, message = "Score must be more than 1!")
	@Pattern(regexp = "[0-9]+(.){0,1}[0-9]*", message = "Score must be a number or decimal!")
	
	@NotEmpty(message = "Student averageScore must be not null!")
	private String averageScore;
	
	

}
