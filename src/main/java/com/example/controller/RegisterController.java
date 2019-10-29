package com.example.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.User;
import com.example.form.RegisterForm;
import com.example.service.UserService;


/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create RegisterController  class
 * @version 1.0
 * @author ToanLM
 */

@Controller
public class RegisterController {
	
	private static final Logger logger = LogManager.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService ;
	
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register(Model model) {
		
		if (logger.isDebugEnabled()) {
            logger.debug("Hello from Log4j 2 - num : {}");
        }
        logger.debug("Hello from Log4j 33 - num : {}");
        
        
        return "register";
    }
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String registerPost(@Valid RegisterForm registerForm,Model model,HttpServletRequest request,BindingResult result) {

		String userName = registerForm.getUserName();
		String password = registerForm.getPassword();
		String passwordConfirm = registerForm.getPasswordConfirm();
		
		User checkUser = userService.getUserByEmail(userName);
		if(checkUser == null){			 
			if(password.equals(passwordConfirm)) {
				
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);
				
				User addUser = new User(userName,hashedPassword,"ROLE_MEMBER");
				userService .save(addUser);

				
				String messString = "Đăng ký thành công";
				String color = "blue";
				 model.addAttribute("color", color);
				 model.addAttribute("messString", messString);
				 
				 return "register";
			}else {
				String messString = "Mật khẩu không trùng nhau";
				 model.addAttribute("messString", messString);
				
				 return "register";  		
			}
			
		}else {
			 String messString = "Tài khoản đã tồn tại";
			 model.addAttribute("messString", messString);
			 return "register";  		
		}
    }
}
