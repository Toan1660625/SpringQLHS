package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.form.AddStudentForm;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create StudentController  class
 * @version 1.0
 * @author ToanLM
 */

@Controller
public class StudentController {

	private static final Logger logger = LogManager.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentInfoService studentInfoService;

	/**
	 * @return view addstudent.html and set data form
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Student(Model model) {
		
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("currentUserName", currentUserName);
		
		model.addAttribute("addStudentForm", new AddStudentForm());
		return "addstudent";
	}

	/**
	 *Handling add student in database
	 *
	 * @param  @Valid EditStudentForm get data down 
	 * @return view addstudent.html
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addStudent(@Valid AddStudentForm addStudentForm, BindingResult result, Model model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Lỗi thêm stdeunt null =====");
			}
			return "addstudent";
		} else {
			Integer studentId = Integer.parseInt(addStudentForm.getStudentId());
			Student checkStudentID = studentService.findById(studentId);					//find student by ID
			if (checkStudentID == null) {

				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

				Student addStudent = new Student();
				addStudent.setStudentId(studentId);
				addStudent.setStudentName(addStudentForm.getStudentName());
				addStudent.setStudentCode(addStudentForm.getStudentCode());
				
				StudentInfo addStudentInfo = new StudentInfo();
				addStudentInfo.setStudent(addStudent);
				addStudentInfo.setAddress(addStudentForm.getAddress());
				Date dateOfBirth;
				try {
					dateOfBirth = formatter.parse(addStudentForm.getBirthDay());
					addStudentInfo.setDateOfBirth(dateOfBirth);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				addStudentInfo.setAverageScore(Float.parseFloat(addStudentForm.getAverageScore()));
				
				try {
					studentInfoService.save(addStudentInfo);								//save student in DB
				} catch (Exception e) {
					return "500";
				} 
				
				logger.debug("======Thêm học sinh thành công======:" + addStudentForm.getStudentCode());
				String messString = "Thêm học sinh thành công";
				model.addAttribute("messString", messString);
				return "addstudent";

			} else {
				logger.debug("======Học sinh đã tồn tại====== :" + addStudentForm.getStudentCode());
				String messString = "Học sinh đã tồn tại";
				model.addAttribute("messString", messString);
				return "addstudent";
			}

		}

	}

}
