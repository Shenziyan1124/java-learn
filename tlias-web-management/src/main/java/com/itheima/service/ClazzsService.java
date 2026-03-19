package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzsService {


    PageResult<Clazz> findAllClazzs(EmpQueryParam empQueryParam);

    void deleteClazzById(Integer id);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazzById(Clazz clazz);

    List<Clazz> clazzsList();
}
