package com.itheima.mapper;

import com.itheima.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    Log page();
}
