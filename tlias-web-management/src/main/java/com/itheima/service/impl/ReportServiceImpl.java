package com.itheima.service.impl;

import com.itheima.mapper.ClazzsMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentsMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzOption;
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
    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private ClazzsMapper clazzsMapper;

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

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentsMapper.getStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData() {

        List<Map<String, Object>> maps = clazzsMapper.getStudentCountData();

        List<Object> clazzList = maps.stream().map(map -> map.get("clazz_name")).toList();
        List<Object> dataList = maps.stream().map(map -> map.get("student_count")).toList();

        return new ClazzOption(clazzList, dataList);
    }
}
