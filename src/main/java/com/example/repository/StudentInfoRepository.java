package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.StudentInfo;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create StudentInfoRepository class
 * @version 1.0
 * @author ToanLM
 */

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>{

}
