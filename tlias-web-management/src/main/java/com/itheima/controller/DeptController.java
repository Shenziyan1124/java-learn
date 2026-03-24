package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/depts")
@RestController
public class DeptController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        List<Dept> deptList = deptService.findAllDept();
        return Result.success(deptList);
    }

    // 删除部门:方式一
    //    @DeleteMapping("/depts")
    //    public Result delete(HttpServletRequest  request) {
    //        String idStr = request.getParameter("id");
    //        Integer id = Integer.parseInt(idStr);
    //         System.out.println("根据id删除部门:"+id);
    //        return Result.success();
    //    }
    //删除部门:方式二
    //    @DeleteMapping("/depts")
    //    public Result delete(@RequestParam("id") Integer id) {
    //        System.out.println("根据id删除部门:" + id);
    //        return Result.success();
    //    }
    //删除部门:方式三
    @DeleteMapping
    @Log
    public Result delete(Integer id) {
//        System.out.println("根据id删除部门:" + id);
        log.info("根据id删除部门:{}", id);
        deptService.deleteDeptById(id);

        return Result.success();
    }


    //添加部门
    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
//        System.out.println("添加部门:" + dept);
        log.info("添加部门:{}", dept);
        deptService.addDept(dept);
        return Result.success();
    }


    //查询某个部门
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId) {
        Dept dept = deptService.getInfo(deptId);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept) {
//        System.out.println("修改部门:" + dept);
        log.info("修改部门:{}", dept);
        deptService.updateDept(dept);
        return Result.success();
    }


}
