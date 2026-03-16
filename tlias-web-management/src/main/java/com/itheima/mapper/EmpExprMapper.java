package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 员工经历
 */
@Mapper
public interface EmpExprMapper {


        //@Insert("insert into emp_expr(emp_id, company, job, begin, end) values(#{empId}, #{company}, #{job}, #{begin}, #{end})")
    void addBatch(List<EmpExpr> exprList);

    void deleteEmpExprByIds(List<Integer> ids);

}
