package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.StudentInfo;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>{

}
