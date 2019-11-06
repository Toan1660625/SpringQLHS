package com.example.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.User;
import com.example.service.StudentInfoService;
import com.example.service.UserService;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create LoginController class
 * @version 1.0
 * @author ToanLM
 */

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logIn(Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String logInPost2(Model model, HttpServletRequest request, HttpSession session) {

		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		User checkUser = userService.findByUserName(currentUserName);
		
		if (checkUser != null) {
			session.setAttribute("userName", currentUserName);
			return "redirect:/index";
		} else {
			String messString = "Tài Khoản hoặc Mật Khẩu không đúng!!";
			model.addAttribute("messString", messString);
			return "login";
		}

	}

// Security auto hanling logout	
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String Logout(Model model, HttpServletRequest request, HttpServletResponse response) {

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		HttpSession session = request.getSession();
//
//		if (session.getAttribute("nameUser") != null) {
//			session.removeAttribute("nameUser");
//			session.invalidate();
//		}
//		if (logger.isDebugEnabled()) {
//			logger.debug( "===== "+session.getAttribute("userName")+" đã logout =====");
//		}
//		return "login";
//	}

}
