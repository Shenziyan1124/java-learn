package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentsQueryParam;

import java.util.List;

public interface StudentsService {
    PageResult<Student> findAllStudents(StudentsQueryParam studentsQueryParam);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudentById(Student student);

    void deleteStudentByIds(List<Integer> ids);

    void violation(Integer id, Integer score);
}
