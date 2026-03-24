package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {


    @Autowired
    private EmpService empService;
    /**
     * 分页查询员工数据
     *
     * @param empQueryParam
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询,参数:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 添加员工
     * @param emp
     * @return
     */
    @PostMapping
    @Log
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工,参数:{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }


    /**
     * 删除员工
     * @param ids
     * @return
     */

//    @DeleteMapping
//    public Result delete(Integer[] ids) {
//        log.info("删除员工,参数:{}", Arrays.toString(ids));
////        empService.deleteEmpById(ids);
//        return Result.success();
//    }

    @DeleteMapping
    @Log
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工,参数:{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }

    /**
     * 查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工,参数:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     * @param emp
     * @return
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工,参数:{}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }


    @GetMapping("/list")
    public Result classTeacherList() {
        log.info("查询所有员工");
        List<EmpSimple> list = empService.classTeacherList();
        return Result.success(list);
    }

}
