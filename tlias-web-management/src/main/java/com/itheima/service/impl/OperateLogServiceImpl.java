package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> page() {
        OperateLog operateLog = new OperateLog();
        PageHelper.startPage(operateLog.getPage(), operateLog.getPageSize());
        List<OperateLog> list = operateLogMapper.page();
//
        Page<OperateLog> logPage = (Page<OperateLog>) list;
        return new PageResult<>(logPage.getTotal(), logPage.getResult());
    }
}
