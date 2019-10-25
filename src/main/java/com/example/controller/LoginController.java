package com.example.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.User;
import com.example.service.StudentInfoService;
import com.example.service.UserService;

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
		User checkUser = userService.getUserByEmail(request.getParameter("username"));
		if(checkUser != null){
			if(checkUser.getPassword().equals(request.getParameter("pass"))) {
				
				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
				String role = checkUser.getRole();
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
				
				session.setAttribute("nameUser", checkUser.getUserName()); 
				return "redirect:/index";
			}else {
				String messString = "Tài khoản hoặc mật khẩu chưa đúng";
				 model.addAttribute("messString", messString);
				 return "login";  		
			}
			
		}else {
			 String messString = "Tài khoản chưa tồn tại";
			 model.addAttribute("messString", messString);
			 return "login";  		
		}
       
    }
}
