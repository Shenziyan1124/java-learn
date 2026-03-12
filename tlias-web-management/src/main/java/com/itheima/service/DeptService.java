package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAllDept();

    void deleteDeptById(Integer id);

    void addDept(Dept dept);

    Dept getInfo(Integer deptId);

    void updateDept(Dept dept);
}
