package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;
import com.example.service.UserService;

@Controller
public class HomeController{
		
	@Autowired
	private StudentInfoService studentInfoService ;
	
	@Autowired
	private StudentService studentService ;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String inDexGet(Model model, HttpServletRequest request) {

		List<StudentInfo> listStudentInfo = studentInfoService.findAll();
		
		int sizeList = listStudentInfo.size();
		
		if(sizeList%3 == 0) {
			sizeList = sizeList/3;
		}else
		{
			sizeList = (sizeList/3) + 1;
		}
		
		model.addAttribute("sizeList",sizeList);
		
		Pageable pageable = PageRequest.of(0, 3);
		
		List<Student> listStudent = studentService.findAllStudent(pageable);
		model.addAttribute("listStudentInfo", listStudent);
		
        return "index";
    }	
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String inDexPost(Model model,HttpServletRequest request) {
		
		List<Student> listStudent = studentService.findAll();
		model.addAttribute("listStudentInfo", listStudent);
        return "index";
    }	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String Logout(Model model,HttpServletRequest request) {

		HttpSession session = request.getSession();
			        if(session.getAttribute("nameUser") != null){
			            session.removeAttribute("nameUser");
			        	session.invalidate();
			        	return "redirect:/login";
			        }
		        return "redirect:/login";
    }
	

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String inDexDelete(Model model,HttpServletRequest request) {
		
	
		int infoId =Integer.parseInt(request.getParameter("infoId"));
		int studentId =Integer.parseInt(request.getParameter("studentId"));
		
		studentInfoService.deleteById(infoId);
		studentService.deleteById(studentId);
		
		List<StudentInfo> listStudentInfo = studentInfoService.findAll();
		
		int sizeList = listStudentInfo.size();
		
		if(sizeList%3 == 0) {
			sizeList = sizeList/3;
		}else
		{
			sizeList = (sizeList/3) + 1;
		}
		
		model.addAttribute("sizeList",sizeList);
		
		Pageable pageable = PageRequest.of(0, 3);
		
		List<Student> listStudent = studentService.findAllStudent(pageable);
		model.addAttribute("listStudentInfo", listStudent);
		
        return "index";
    }
	
	
	
	
	
	
	@RequestMapping(value = "/find/{pageNumber}", method = RequestMethod.GET)
	public String inDexFind(Model model,HttpServletRequest request,@PathVariable("pageNumber") int pageNumber,@PathParam("findCode") String findCode) {
		HttpSession session = request.getSession();
//		String findName = request.getParameter("findName");
		if(pageNumber == 0) {
			session.setAttribute("findCode", findCode);
			pageNumber = pageNumber+1;
		}
		
		findCode = (String)session.getAttribute("findCode");
		
		List<Student> listStudentFindAll = studentService.findByStudentCodeLike(findCode);
		int sizeList = listStudentFindAll.size();
		
		if(sizeList%3 == 0) {
			sizeList = sizeList/3;
		}else
		{
			sizeList = (sizeList/3) + 1;
		}
		
		model.addAttribute("sizeList",sizeList);
		
		Pageable pageable = PageRequest.of(pageNumber-1, 3);
		
		List<Student> listStudent = studentService.findByStudentCodeLike(findCode, pageable);
		
		model.addAttribute("listStudentInfo", listStudent);
		
        return "find";

//		List<Student> listStudent = studentService.findByStudentName(findName);
//		model.addAttribute("listStudentInfo", listStudent);

    }
	
	
	@RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
	public String inDexPage(Model model,HttpServletRequest request,@PathVariable("pageNumber") int pageNumber ) {

//		int start = (pageNumber-1)*3 + 1;             Công thức tính page nếu cần
		
		List<StudentInfo> listStudentAll = studentInfoService.findAll();
		int sizeList = listStudentAll.size();
		if(sizeList%3 == 0) {
			sizeList = sizeList/3;
		}else
		{
			sizeList = (sizeList/3) + 1;
		}
		model.addAttribute("sizeList",sizeList);

		Pageable pageable = PageRequest.of(pageNumber-1, 3);
		
		List<Student> listStudent = studentService.findAllStudent(pageable);
		
		model.addAttribute("listStudentInfo", listStudent);
		
        return "index";
    }

}
