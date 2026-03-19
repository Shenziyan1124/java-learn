package com.itheima.controller;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 员工职位统计
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 员工性别统计
     * @return
     */

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }


    /**
     * 学员统计
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        List<Map<String, Object>> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }
    /**
     * 班级统计
     */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        ClazzOption list = reportService.getStudentCountData();
        return Result.success(list);
    }

}
