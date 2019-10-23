package com.example.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentInfo")
public class StudentInfo implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "infoId", nullable = false)
	private int infoId;

	@OneToOne
    @JoinColumn(name = "studentId")
	private Student student;
	
	@Column(name = "address",length = 255)
	private String address;
	
	@Column(name = "averageScore")
	private float averageScore;
	
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;



	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public int getInfoId() {
		return infoId;
	}	
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public String getDateOfBirthToString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String date = formatter.format(dateOfBirth);
		return date;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
