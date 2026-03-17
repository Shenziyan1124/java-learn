package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
     List<Emp> list(EmpQueryParam empQueryParam);

     /**
     * 添加员工
     * */
   @Options(useGeneratedKeys = true, keyProperty = "id")
   @Insert("insert into emp(username, name, gender, phone, job, salary,image, entry_date, dept_id, create_time, update_time) " +
           "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary},#{image}, #{entryDate},#{deptId},#{createTime},#{updateTime} )")
    void addEmp(Emp emp);

   /**
     * 批量删除员工
     * */
    void deleteEmpByIds(List<Integer> ids);


    /**
     * 根据id查询员工
     * */
    Emp getById(Integer id);

    /**
     * 修改员工
     * */
    void updateEmp(Emp emp);

    /**
     * 统计员工职位人数
     * */
    @MapKey("job")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * */

    List<Map<String, Object>> getEmpGenderData();
}

