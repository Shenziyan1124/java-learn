package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult page(Integer page, Integer pageSize,
//                    String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void addEmp(Emp emp);

    void deleteEmp(List<Integer> ids);

    Emp getById(Integer id);

    void updateEmp(Emp emp);
}
