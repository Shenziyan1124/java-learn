package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzsMapper {
    List<Clazz> findAllClazzs(EmpQueryParam empQueryParam);

    void deleteClazzById(Integer id);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazzById(Clazz clazz);

    List<Clazz> clazzsList();

    Integer countStudentByClazzId(Integer id);
}
