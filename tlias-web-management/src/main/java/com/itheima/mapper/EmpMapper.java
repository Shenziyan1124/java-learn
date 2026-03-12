package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工基本信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询员工总记录数
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    Long count();
    /**
     * 分页查询员工数据
     */
//    @Select("select * from emp")
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc " +
//            "limit #{start},#{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);


    /**
     * 分页
     * */
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
     //           "order by e.update_time desc ")
     List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
}
