package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService ;
	
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register(Model model) {
        return "register";
    }
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String registerPost(Model model,HttpServletRequest request) {
	
		User checkUser = userService.getUserByEmail(request.getParameter("userName"));
		if(checkUser == null){			 
			if(request.getParameter("pass").equals(request.getParameter("passConfirm"))) {
				User addUser = new User(request.getParameter("userName"),request.getParameter("pass"));
				userService .save(addUser);
				
				String messString = "Đăng ký thành công";
				String color = "blue";
				 model.addAttribute("color", color);
				 model.addAttribute("messString", messString);
				 
				 return "register";
			}else {
				String messString = "Mật khẩu không trùng nhau";
				 model.addAttribute("messString", messString);
				
				System.out.println("dang kyss");
				 return "register";  		
			}
			
		}else {
			 String messString = "Tài khoản đã tồn tại";
			 model.addAttribute("messString", messString);

			 return "register";  		
		}
    }
}
