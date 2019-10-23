package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.StudentInfo;
import com.example.entity.Student;
import com.example.service.StudentInfoService;
import com.example.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentInfoService studentInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Student(Model model) {

		return "addstudent";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addStudent(Model model, HttpServletRequest request) {

		Integer studentId = Integer.parseInt(request.getParameter("studentId"));
		Student checkStudentID = studentService.findById(studentId);
		if (checkStudentID == null) {

			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

			Student addStudent = new Student();
			addStudent.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			System.out.println("kkkkkkkk: "+ Integer.parseInt(request.getParameter("studentId")));
			addStudent.setStudentName(request.getParameter("studentName"));
			addStudent.setStudentCode(request.getParameter("studentCode"));
			studentService.save(addStudent);

			StudentInfo addStudentInfo = new StudentInfo();
			addStudentInfo.setStudent(addStudent);
			addStudentInfo.setAddress(request.getParameter("address"));
			Date dateOfBirth;
			try {
				dateOfBirth = formatter.parse(request.getParameter("birthDay"));
				addStudentInfo.setDateOfBirth(dateOfBirth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addStudentInfo.setAverageScore(Float.parseFloat(request.getParameter("averageScore")));
			studentInfoService.save(addStudentInfo);

			String messString = "Thêm học sinh thành công";
			 model.addAttribute("messString", messString);
			return "addstudent";

		} else {
			String messString = "Học sinh đã tồn tại";
			 model.addAttribute("messString", messString);
			return "addstudent";
		}
	}

}
