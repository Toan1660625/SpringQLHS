package com.example.controller;
/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create RegisterController  class
 * @version 1.0
 * @author ToanLM
 */

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.DataSearchAjax;
import com.example.entity.Student;
import com.example.form.SearchForm;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;

//@Controller

@RestController
public class SearchAjaxController {

	private static final Logger logger = LogManager.getLogger(SearchAjaxController.class);

	@Autowired
	private StudentService studentService;

	StudentInfoService studentInfoService;

	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	// Page Search bằng ajax
//	@PostMapping("/api/search")
//	public String searchAjax(@Valid SearchForm search, Errors errors,HttpSession http) {
	
	
//	@PostMapping(path = "/api/search",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
//	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
	@GetMapping("/api/search")
	public ResponseEntity<?> searchAjax(@Valid SearchForm search, Errors errors, HttpSession http) {
		
		DataSearchAjax data = new DataSearchAjax();
		if (logger.isDebugEnabled()) {
			logger.debug("=====  Ajax Search ====");
		}
	
		if (errors.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Have error when search with Ajax= ===="+ search.getStudentName() );
			}
			data.setMessage(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return  ResponseEntity.badRequest().body(data);

		}
		Pageable pageable = PageRequest.of(0, 10);
		List<Student> listStudent = studentService.findByStudentName(search.getStudentName(), pageable);
		if (listStudent.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Not found student: " + search.getStudentName() + " =====");
			}
			data.setMessage("Student not found!");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Find student: " + search.getStudentName() + " success. =====");
			}
			http.setAttribute("search", search.getStudentName());
			
			List<Student> listStudentName = studentService.findByStudentName(search.getStudentName());
			int totalPage = studentService.pageNumber(listStudentName.size());

			http.setAttribute("total", totalPage);
			data.setMessage("success");
			data.setTotalPage(totalPage);
			data.setTotalStudent(listStudentName.size());
		}
		data.setData(listStudent);
		
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + data.getMessage());
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + data.getTotalPage());
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + data.getTotalStudent());
		for( Student e : data.getData() )
		{
			System.out.println("oooooooooooooooooooooooooooo" + e.getStudentName());
		}	
		return  ResponseEntity.ok(data);

	}


}
