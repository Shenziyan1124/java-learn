package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
//    @Results({
//            @Result(column = "update_time", property = "updateTime"),
//            @Result(column = "create_time", property = "createTime")
//    })
//    createTime,
//    updateTime
    @Select("select id, name, create_time ,update_time  from dept order by update_time desc")
    List<Dept> findAllDept();

    @Delete("delete from dept where id = #{id}")
    void deleteDeptById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    @Select("select id, name, create_time ,update_time  from dept where id = #{deptId}")
    Dept getInfo(Integer deptId);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
