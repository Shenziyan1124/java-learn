package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {

        List<Map<String, Object>> maps = empMapper.countEmpJobData();

        List<Object> jobList = maps.stream().map(map -> map.get("job")).toList();
        List<Object> dataList = maps.stream().map(map -> map.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {


        return empMapper.getEmpGenderData();
    }
}
