package com.example.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;
import com.example.entity.StudentInfo;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create StudentRepository class get Data from Table student
 * @version 1.0
 * @author ToanLM
 */

@Repository
public interface StudentRepository extends CrudRepository<Student, Serializable> {
	// Chỗ Serializable là chỗ các khóa chính trong các hàm được truyền vào kiểu
	// param gì

	List<Student> findAll();

	@Query(value = "SELECT * FROM student WHERE student.student_code LIKE %?1%", nativeQuery = true)
	List<Student> findByStudentCodeLike(String studentCode, Pageable pageable);

	@Query(value = "SELECT * FROM student WHERE student.student_name LIKE %?1%", nativeQuery = true)
	List<Student> findByStudentName(String studentName, Pageable pageable);

	@Query(value = "SELECT * FROM student WHERE student.student_code LIKE %?1%", nativeQuery = true)
	List<Student> findByStudentCodeLike(String studentCode);

	@Query(value = "SELECT * FROM student WHERE student.student_name LIKE %?1%", nativeQuery = true)
	List<Student> findByStudentName(String studentName);

	@Query(value = "SELECT * FROM student", nativeQuery = true)
	List<Student> findAllStudent(Pageable pageable);

	Optional<Student> findByStudentCode(String studentCode); // findBy.. + name column (exactly)

	@Query(name = "HQL_GET_ALL_STUDENT_BY_NAME", nativeQuery = true) // findByStudentNameHQL("%6%"); Have % when query
	List<Student> findByStudentNameHQL(@Param("name") String studentName);

	@Query(name = "HQL_GET_ALL_STUDENT", nativeQuery = true)
	List<Student> findAllHQL();
}
