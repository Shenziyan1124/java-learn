package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzsMapper {
    List<Clazz> findAllClazzs(EmpQueryParam empQueryParam);

    void deleteClazzById(Integer id);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazzById(Clazz clazz);

    List<Clazz> clazzsList();

    Integer countStudentByClazzId(Integer id);

    List<Map<String, Object>> getStudentCountData();
}
