package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.entity.User;
import com.example.repository.StudentInfoRepository;

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

    public void save(StudentInfo entity) {
        try {
            studentInfoReponsitory.save(entity);
        } catch (Exception e) {
            System.out.println("Lỗi save studentInfo");
        }
    }

    public void deleteById(Integer id) {
        Optional<StudentInfo> studentInfo = studentInfoReponsitory.findById(id);
        studentInfoReponsitory.deleteById(id);
        if (studentInfo.isPresent()) {
            studentInfo.get();
        } else {
            System.out.println("Học sinh chưa có để  xóa");
            ;
        }

    }

}
