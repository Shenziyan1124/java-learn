package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentsQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentsMapper {
    List<Student> findAllStudents(StudentsQueryParam studentsQueryParam);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudentById(Student student);

    void deleteStudentByIds(List<Integer> ids);

    void violation(Integer id, Integer score);
}
