package com.example.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private UserService userService ;

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String logIn(Model model) {
        return "login";
    }
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String logInPost(Model model,HttpServletRequest request,HttpSession session) {
		
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(request.getParameter("password"));

//		User checkUser = userService.findByUserName(request.getParameter("userName"));
//
//		if(checkUser != null){
//			if(checkUser.getPassword().equals(hashedPassword)) {	
//						session.setAttribute("nameUser", checkUser.getUserName()); 
//				return "redirect:/index";
//			}else {
//				String messString = "Tài khoản hoặc mật khẩu chưa đúng";
//				 model.addAttribute("messString", messString);
//				 return "login";  		
//			}
//
//		}else {
//			 String messString = "Tài khoản chưa tồn tại";
//			 model.addAttribute("messString", messString);
//			 return "login";  		
//		}
		User checkUser = userService.findByUserName(request.getParameter("username"));
		session.setAttribute("nameUser", checkUser.getUserName()); 
		return "redirect:/index";
       
    }
	
	
	
	@RequestMapping(value = "/loginSuccess",method = RequestMethod.GET)
	public String logInPost2(Model model,HttpServletRequest request,HttpSession session) {
		User checkUser = userService.findByUserName(request.getParameter("username"));
		session.setAttribute("nameUser", checkUser.getUserName()); 
		return "redirect:/register";
       
    }
	
	@RequestMapping(value = "/loginSuccess",method = RequestMethod.POST)
	public String logInPost3(Model model,HttpServletRequest request,HttpSession session) {
				User checkUser = userService.findByUserName(request.getParameter("username"));
				session.setAttribute("nameUser", checkUser.getUserName()); 
				return "redirect:/index";
 
    }
	
	
}
