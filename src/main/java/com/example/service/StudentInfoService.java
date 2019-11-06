package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.entity.User;
import com.example.repository.StudentInfoRepository;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  StudentInfoService class
 * @version 1.0
 * @author ToanLM
 */

@Service
public class StudentInfoService {

	@Autowired
	StudentInfoRepository studentInfoReponsitory;

	public List<StudentInfo> findAll() {
		List<StudentInfo> studentInfoList = studentInfoReponsitory.findAll();

		if (studentInfoList.size() > 0) {
			return studentInfoList;
		} else {
			return new ArrayList<StudentInfo>();
		}
	}

	public StudentInfo findById(Integer id) {
		Optional<StudentInfo> studentInfo = studentInfoReponsitory.findById(id);
		if (studentInfo.isPresent()) {
			return studentInfo.get();
		} else {
			return null;
		}
	}

	// Không nên trong try/catch vì có lỗi sẽ chạy zo Exception e và hàm
	// Transactional sẽ không bắt được lỗi để Rollback(không có lỗi)
	@Transactional(rollbackFor = { Exception.class })
	public void save(StudentInfo entity) {
//		try {
//			studentInfoReponsitory.save(entity);
//			int a = 10/0;
//		} catch (Exception e) {
//			System.out.println("Lỗi save studentInfo");
//		}
		studentInfoReponsitory.save(entity);

	}

	@Transactional(rollbackFor = { Exception.class })
	public void deleteById(Integer id) {
		Optional<StudentInfo> studentInfo = studentInfoReponsitory.findById(id);
		if (studentInfo.isPresent()) {
			studentInfoReponsitory.deleteById(id);
		} else {
			System.out.println("Học sinh chưa có để  xóa");
		}
	}

}
