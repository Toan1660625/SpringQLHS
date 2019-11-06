package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.form.AddStudentForm;
import com.example.form.EditStudentForm;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create EditController class
 * @version 1.0
 * @author ToanLM
 */

@Controller
public class EditController {

	private static final Logger logger = LogManager.getLogger(EditController.class);
	@Autowired
	private StudentInfoService studentInfoService;

	@Autowired
	private StudentService studentService;

	//load form edit student set data input
	@RequestMapping(value = "/edit/{infoId}", method = RequestMethod.GET)
	public String inDexEditGet(Model model, HttpServletRequest request, @PathVariable("infoId") int infoId) {
		StudentInfo studentInfo = studentInfoService.findById(infoId);
		EditStudentForm editStudentForm = new EditStudentForm();
		
		String studentId = String.valueOf(studentInfo.getStudent().getStudentId());
		String studentCode = studentInfo.getStudent().getStudentCode();
		String studentName =  studentInfo.getStudent().getStudentName();
		String address = studentInfo.getAddress();
		String averageScore = String.valueOf(studentInfo.getAverageScore());
		String birthDay = studentInfo.getDateOfBirthToString();
		
		//set data from edit student
		editStudentForm.setStudentId(studentId);
		editStudentForm.setStudentCode(studentCode);
		editStudentForm.setStudentName(studentName);
		editStudentForm.setAddress(address);
		editStudentForm.setAverageScore(averageScore);
		editStudentForm.setBirthDay(birthDay);
		
		model.addAttribute("editStudentForm", editStudentForm);
		return "editstudent";
	}

	//Edit student in database
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String inDexEditPost(@Valid EditStudentForm editStudentForm, BindingResult result, Model model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Lỗi edit stdeunt null =====");
			}
			return "editstudent";
		} else {
			int studentId = Integer.parseInt(editStudentForm.getStudentId());
			Student student = studentService.findById(studentId);
			StudentInfo studentInfo = studentInfoService.findById(student.getStudentInfo().getInfoId());		//find by student Id

			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

			student.setStudentName(editStudentForm.getStudentName());
			student.setStudentCode(editStudentForm.getStudentCode());

			studentInfo.setStudent(student);
			studentInfo.setAddress(editStudentForm.getAddress());
			Date dateOfBirth;
			try {
				dateOfBirth = formatter.parse(editStudentForm.getBirthDay());
				studentInfo.setDateOfBirth(dateOfBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			studentInfo.setAverageScore(Float.parseFloat(editStudentForm.getAverageScore()));	
			try {
				studentInfoService.save(studentInfo);
			} catch (Exception e) {
				return "500";
			} 
			
			logger.debug("======Sữa học sinh thành công======:"+ editStudentForm.getStudentCode());
			model.addAttribute("studentInfo", studentInfo);
			String messString = "Sửa học sinh thành công";
			model.addAttribute("messString", messString);

			return "editstudent";
		}
		

	}

}
