package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.pojo.Log;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl  implements LogService {
    @Override
    public PageResult<Log> page() {
//        PageHelper.startPage(Log.getPage(), Log.getPageSize());
//        List<Log> list = logMapper.page();
//
//        Page<Log> logPage = (Page<Log>) list;
//        return new PageResult<>(logPage.getTotal(), logPage.getResult());
        return  null;
    }
}
