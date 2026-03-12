package com.itheima.service;

import com.itheima.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
    PageResult page(Integer page, Integer pageSize,
                    String name, Integer gender, LocalDate begin, LocalDate end);
}
