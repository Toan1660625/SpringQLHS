package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.StudentInfo;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create StudentInfoRepository class get Data from Table studentinfo
 * @version 1.0
 * @author ToanLM
 */

@Repository
public interface StudentInfoRepository extends CrudRepository<StudentInfo, Serializable> {

	List<StudentInfo> findAll();
}
