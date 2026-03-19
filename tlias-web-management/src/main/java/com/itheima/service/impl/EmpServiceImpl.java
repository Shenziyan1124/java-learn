package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.compiler.ast.Expr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    //    @Override
    //    public PageResult<Emp> page(Integer page, Integer pageSize,
    //                                String name, Integer gender, LocalDate begin, LocalDate end) {

    /// /        Long total = empMapper.count();
    /// /        Integer start = (page - 1) * pageSize;
    /// /        List<Emp> rows = empMapper.list(start, pageSize);
    //
    //        PageHelper.startPage(page, pageSize);
    //        List<Emp> empList = empMapper.list(name, gender, begin, end);
    //        Page<Emp> empPage = (Page<Emp>) empList;
    //        return new PageResult<>(empPage.getTotal(), empPage.getResult());
    //    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageResult<>(empPage.getTotal(), empPage.getResult());
    }

    @Transactional(rollbackFor = Exception.class) // 事务管理
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);

        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)) {
            empExprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.addBatch(empExprList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmp(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteEmpByIds(ids);
        //2.批量删除员工对应的员工入职信息
        empExprMapper.deleteEmpExprByIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmp(Emp emp) {
        //1.更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
        //2.更新员工入职信息
        //2.1 删除员工对应的员工入职信息
        empExprMapper.deleteEmpExprByIds(Arrays.asList(emp.getId()));
        //2.2 添加员工对应的员工入职信息
        //循环为每个员工入职信息添加员工id
        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)){
            empExprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.addBatch(empExprList);
        }

    }

    @Override
    public List<EmpSimple> classTeacherList() {

        return empMapper.classTeacherList();
    }
}
