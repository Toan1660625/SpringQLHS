package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create StudentRepository class
 * @version 1.0
 * @author ToanLM
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query(value = "SELECT * FROM student WHERE student.student_code LIKE %?1%",nativeQuery = true)
	List<Student> findByStudentCodeLike(String studentCode,Pageable pageable);
	
	@Query(value = "SELECT * FROM student WHERE student.student_name LIKE %?1%",nativeQuery = true)
	List<Student> findByStudentName(String studentName,Pageable pageable);
	

	@Query(value = "SELECT * FROM student WHERE student.student_code LIKE %?1%",nativeQuery = true)
	List<Student> findByStudentCodeLike(String studentCode);
	
	@Query(value = "SELECT * FROM student WHERE student.student_name LIKE %?1%",nativeQuery = true)
	List<Student> findByStudentName(String studentName);
	
	@Query(value = "SELECT * FROM student",nativeQuery = true)  
	List<Student> findAllStudent(Pageable pageable);
	
	Optional<Student> findByStudentCode(String studentCode); // findBy.. + Ten Cot la truy van ra cot do
}
