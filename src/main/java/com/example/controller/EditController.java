package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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
public class EditController {
	
	@Autowired
	private StudentInfoService studentInfoService ;
	
	@Autowired
	private StudentService studentService ;
	
	@RequestMapping(value = "/edit/{infoId}", method = RequestMethod.GET)
	public String inDexEditGet(Model model,HttpServletRequest request,@PathVariable("infoId") int infoId) {
		
		StudentInfo studentInfo = studentInfoService.findById(infoId);
		
		model.addAttribute("studentInfo", studentInfo);
        return "editstudent";
    }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String inDexEditPost(Model model,HttpServletRequest request) {

		int studentId =Integer.parseInt(request.getParameter("studentId"));
		Student student = studentService.findById(studentId);
		StudentInfo studentInfo = studentInfoService.findById(student.getStudentInfo().getInfoId());

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

		student.setStudentName(request.getParameter("studentName"));
		student.setStudentCode(request.getParameter("studentCode"));
		studentService.save(student);

		studentInfo.setStudent(student);
		studentInfo.setAddress(request.getParameter("address"));
		Date dateOfBirth;
		try {
			dateOfBirth = formatter.parse(request.getParameter("birthDay"));
			studentInfo.setDateOfBirth(dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentInfo.setAverageScore(Float.parseFloat(request.getParameter("averageScore")));
		studentInfoService.save(studentInfo);

		model.addAttribute("studentInfo", studentInfo);
		String messString = "Sửa học sinh thành công";
		 model.addAttribute("messString", messString);
		
		
        return "editstudent";
    }

}
