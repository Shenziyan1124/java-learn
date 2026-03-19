package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentsMapper;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentsQueryParam;
import com.itheima.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @Override
    public PageResult<Student> findAllStudents(StudentsQueryParam studentsQueryParam) {
        PageHelper.startPage(studentsQueryParam.getPage(), studentsQueryParam.getPageSize());
        List<Student> list = studentsMapper.findAllStudents(studentsQueryParam);

        Page<Student> studentPage = (Page<Student>) list;
        return new PageResult<>(studentPage.getTotal(), studentPage.getResult());

    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentsMapper.addStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentsMapper.getStudentById(id);
    }

    @Override
    public void updateStudentById(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.updateStudentById(student);
    }

    @Override
    public void deleteStudentByIds(List<Integer> ids) {
        studentsMapper.deleteStudentByIds(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {

        if (score < 0){
            throw new RuntimeException("扣分不能小于0");
        }
        if (score > 100){
            throw new RuntimeException("扣分不能大于100");
        }
        studentsMapper.violation(id, score);
    }
}
