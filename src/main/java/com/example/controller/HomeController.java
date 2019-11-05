package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create HomeController class
 * @version 1.0
 * @author ToanLM
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private StudentInfoService studentInfoService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public String inDexGet(Model model, HttpServletRequest request,HttpSession session) {
		
		if (logger.isDebugEnabled()) {
			logger.debug( "===== "+session.getAttribute("userName")+" vào trang chủ =====");
		}
		
		List<Student> listStudentHQL = studentService.findAllHQL();
		int sizeList = studentService.pageNumber(listStudentHQL.size()); // Số page của tổng danh sách
		model.addAttribute("sizeList", sizeList);
		
		Pageable pageable = PageRequest.of(0, 3);
		List<Student> listStudent = studentService.findAllStudent(pageable);
		model.addAttribute("listStudentInfo", listStudent);

		return "index";
	}

	@RequestMapping(value = "/delete/{infoId}", method = RequestMethod.GET)
	public String inDexDelete(Model model, HttpServletRequest request, @PathVariable("infoId") int infoId,HttpSession session) {
		
		try {
			studentInfoService.deleteById(infoId);
			if (logger.isDebugEnabled()) {
				logger.debug( "===== "+session.getAttribute("userName")+" đã xóa 1 học sinh =====");
			}
		} catch (Exception e) {
			return "500";
		}

		List<StudentInfo> listStudentInfo = studentInfoService.findAll();
		int sizeList = studentService.pageNumber(listStudentInfo.size());
		model.addAttribute("sizeList", sizeList);

		Pageable pageable = PageRequest.of(0, 3);
		List<Student> listStudent = studentService.findAllStudent(pageable);
		model.addAttribute("listStudentInfo", listStudent);

		return "index";
	}

	
	//Page Search bằng java
	@RequestMapping(value = "/find/{pageNumber}", method = RequestMethod.POST)
	public String inDexFind(Model model, HttpServletRequest request, @PathVariable("pageNumber") int pageNumber,
			@PathParam("findCode") String findCode,HttpSession session) {
		
		if (pageNumber == 0) {
			session.setAttribute("findCode", findCode);
			pageNumber = pageNumber + 1;
		}

		findCode = (String) session.getAttribute("findCode");
		List<Student> listStudentFindAll = studentService.findByStudentCodeLike(findCode);
		int sizeList = studentService.pageNumber(listStudentFindAll.size());
		model.addAttribute("sizeList", sizeList);

		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
		List<Student> listStudent = studentService.findByStudentCodeLike(findCode, pageable);
		model.addAttribute("listStudentInfo", listStudent);

		if (logger.isDebugEnabled()) {
			logger.debug( "===== "+session.getAttribute("userName")+" vừa tìm kiếm =====");
		}
		
		return "find";

	}

	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	public String inDexPage(Model model, HttpServletRequest request, @PathVariable("pageNumber") int pageNumber) {

		List<StudentInfo> listStudentAll = studentInfoService.findAll();
		int sizeList = studentService.pageNumber(listStudentAll.size());
		model.addAttribute("sizeList", sizeList);

		Pageable pageable = PageRequest.of(pageNumber - 1, 3);

		List<Student> listStudent = studentService.findAllStudent(pageable);
		model.addAttribute("listStudentInfo", listStudent);
		return "index";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error403() {
		return "403";
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String error500() {
		return "500";
	}

}
