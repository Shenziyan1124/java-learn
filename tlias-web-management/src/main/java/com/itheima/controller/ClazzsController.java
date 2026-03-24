package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzsController {

    @Autowired
    private ClazzsService clazzsService;

    /**
     * 查询所有班级信息
     *
     * @return
     */
    @GetMapping
    public Result findAllClazzs(EmpQueryParam empQueryParam) {
        PageResult<Clazz> list = clazzsService.findAllClazzs(empQueryParam);
        log.info("{}", list);
        return Result.success(list);
    }

    /**
     *  删除班级
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级:{}", id);
        clazzsService.deleteClazzById(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @PostMapping
    @Log
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级:{}", clazz);
        clazzsService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("查询班级,参数:{}", id);
        Clazz clazz = clazzsService.getClazzById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级:{}", clazz);
        clazzsService.updateClazzById(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     * */
    @GetMapping("/list")
    public Result clazzsList() {
        List<Clazz> clazzList = clazzsService.clazzsList();
        return Result.success(clazzList);
    }




}
