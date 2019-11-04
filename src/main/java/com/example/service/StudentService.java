package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  StudentService class
 * @version 1.0
 * @author ToanLM
 */

@Service
public class StudentService {

	@Autowired
	StudentRepository studentReponsitory;

	public List<Student> findAll() {
		List<Student> studentList = studentReponsitory.findAll();

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}
	}

	public Student findById(Integer id) {
		Optional<Student> student = studentReponsitory.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			return null;
		}

	}

	public Student findBystudentCode(String studentCode) {
		Optional<Student> student = studentReponsitory.findByStudentCode(studentCode);
		if (student.isPresent()) {
			return student.get();
		} else {
			return null;
		}

	}

	public List<Student> findByStudentName(String studentName) {
		List<Student> studentList = studentReponsitory.findByStudentName(studentName);

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}

	}

	public List<Student> findByStudentCodeLike(String studentCode) {
		List<Student> studentList = studentReponsitory.findByStudentCodeLike(studentCode);

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}

	}

	public List<Student> findByStudentName(String studentName, Pageable pageable) {
		List<Student> studentList = studentReponsitory.findByStudentName(studentName, pageable);

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}

	}

	public List<Student> findByStudentCodeLike(String studentCode, Pageable pageable) {
		List<Student> studentList = studentReponsitory.findByStudentCodeLike(studentCode, pageable);

		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}

	}

	@Transactional(rollbackFor = {Exception.class})
	public void save(Student entity) {
		try {
			studentReponsitory.save(entity);
		} catch (Exception e) {
			System.out.println("Lỗi save Student");
		}
	}

	@Transactional(rollbackFor = {Exception.class})
	public void deleteById(Integer id) {
		Optional<Student> student = studentReponsitory.findById(id);
		if (student.isPresent()) {
			studentReponsitory.deleteById(id);
		} else {
			System.out.println("Học sinh chưa có để xóa");
		}

	}

	public List<Student> findAllStudent(Pageable pageable) {
		List<Student> studentList = studentReponsitory.findAllStudent(pageable);
		return studentList;
	}

	// Hàm lấy ra số Page của 1 List
	public int pageNumber(int sizeList) {
		if (sizeList % 3 == 0) {
			return sizeList = sizeList / 3;
		} else {
			return sizeList = (sizeList / 3) + 1;
		}
	}

}
