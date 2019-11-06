package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.DataSearchAjax;
import com.example.entity.Student;
import com.example.form.SearchForm;
import com.example.service.StudentService;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create SearchAjaxController  class
 * @version 1.0
 * @author ToanLM
 */

@RestController
public class SearchAjaxController {

	private static final Logger logger = LogManager.getLogger(SearchAjaxController.class);

	private StudentService studentService;

	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping(path = "/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchForm search, BindingResult errors,
			HttpSession http) {

		DataSearchAjax data = new DataSearchAjax();
		if (errors.hasErrors()) {
			if (logger.isDebugEnabled()) {
				logger.debug("===== Have error when search with Ajax= ===="+ search.getStudentName());
			}
			data.setMessage(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return  ResponseEntity.badRequest().body(data);

		}
		Pageable pageable = PageRequest.of(0, 3);
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
		data.setResult(listStudent);
		return ResponseEntity.ok(data);

	}

	@GetMapping("/api/search/page/{page}")
	public ResponseEntity<?> getSearchAjax(@PathVariable("page") int page, HttpSession http) {
		DataSearchAjax result = new DataSearchAjax();
		String search = (String) http.getAttribute("search");

		List<Student> listStudentName = studentService.findByStudentName(search);
		int totalPage = studentService.pageNumber(listStudentName.size());

		Pageable pageable = PageRequest.of(page, 3);

		List<Student> student = studentService.findByStudentName(search, pageable);
		if (student.isEmpty()) {
			result.setMessage("Student not found!");
		} else {
			result.setMessage("success");
			result.setTotalPage(totalPage);
			result.setTotalStudent(student.size());
		}
		result.setResult(student);

		return ResponseEntity.ok(result);
	}

}
