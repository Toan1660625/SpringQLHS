package com.example.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create EditStudentForm class
 * @version 1.0
 * @author ToanLM
 */
public class EditStudentForm {

	@NotEmpty(message = "Student id must be not null!")
	private String studentId;

	@Length(max = 10, message = "Code must be less than 10!")
	@NotEmpty(message = "Student code must be not null!")
	private String studentCode;

	@Length(max = 20, message = "Name must be less than 20!")
	@NotEmpty(message = "Student name must be not null!")
	private String studentName;

	@NotEmpty(message = "Student address must be not null!")
	private String address;

	@Max(value = 10, message = "Score must be less than 10!")
	@Min(value = 1, message = "Score must be more than 1!")
	@Pattern(regexp = "[0-9]+(.){0,1}[0-9]*", message = "Score must be a number or decimal!")
	private String averageScore;

	@NotEmpty(message = "Student birthDay must be not null!")
	private String birthDay;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

}
