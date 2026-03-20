package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfor;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    @RequestMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录{}", emp);
        LoginInfor information = empService.login(emp);

        if (information != null){
            return Result.success(information);
        }else {
            return Result.error("用户名或密码错误");
        }

    }
}
