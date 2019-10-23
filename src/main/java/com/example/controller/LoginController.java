package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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
		User checkUser = userService.getUserByEmailHQL(request.getParameter("username"));
		if(checkUser != null){
			if(checkUser.getPassword().equals(request.getParameter("pass"))) {
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
