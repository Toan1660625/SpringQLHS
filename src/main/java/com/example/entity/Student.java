package com.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  Student class
 * @version 1.0
 * @author ToanLM
 */

@Entity
@Table(name = "student")
public class Student implements java.io.Serializable {

	@Id
	@Column(name = "studentId", nullable = false)
	private int studentId;

	@Column(name = "studentName", length = 20)
	private String studentName;

	@Column(name = "studentCode", length = 10)
	private String studentCode;

	@OneToOne(mappedBy = "student") // mappedBy to student in studentInfo
	private StudentInfo studentInfo;

	@JsonManagedReference
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public Student() {
	}

	public Student(String studentCode, String studentName) {
		this.studentCode = studentCode;
		this.studentName = studentName;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
