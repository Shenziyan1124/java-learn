package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentsQueryParam;
import com.itheima.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    /**
     * 分页查询所有学生信息
     */
    @GetMapping
    public Result findAllStudents(StudentsQueryParam studentsQueryParam) {
        PageResult<Student> list = studentsService.findAllStudents(studentsQueryParam);
        return Result.success(list);
    }
    /**
     * 添加学生信息
     */
//    @PostMapping
//    public Result addStudent(@RequestBody Student student) {
//        log.info("添加学生信息:{}", student);
//        studentsService.addStudent(student);
//        return Result.success();
//    }

    /**
     * id 查询学生信息
     */
    @GetMapping("/{id}")
    public Result findStudentById(@PathVariable Integer id) {
        log.info("id查询学生信息:{}", id);
        Student student = studentsService.getStudentById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生信息 (PUT):{}", student);
        studentsService.updateStudentById(student);
        return Result.success();
    }

    /**
     * 添加或修改学生信息 - 兼容前端
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("保存学生信息:{}", student);

        if (student.getNo() != null) {
            // 有 id，说明是修改操作
            log.info("执行修改操作，id:{}", student.getId());
            studentsService.updateStudentById(student);
        } else {
            // 没有 id，说明是添加操作
            log.info("执行添加操作");
            studentsService.addStudent(student);
        }

        return Result.success();
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("删除学生信息:{}", ids);

        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .toList();

        studentsService.deleteStudentByIds(idList);
        return Result.success();
    }


    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学生id:{},扣分:{}", id, score);
        studentsService.violation(id, score);
        return Result.success();
    }


}
