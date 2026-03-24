package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect1 {
    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    private void pt(){}

    @Before("pt()")
    public void before() {
        log.info("Before...");
    }
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("开始记录日志...");
        Object result = pjp.proceed();
        log.info("结束记录日志...");
        return result;
    }
    @After("pt()")
    public void after() {
        log.info("After...");
    }

    // 返回通知 只有正常返回才会执行
    @AfterReturning("pt()")
    public void afterReturning() {
        log.info("AfterReturning...");
    }

    // 异常通知 只有异常才会执行
    @AfterThrowing("pt()")
    public void afterThrowing() {
        log.info("AfterThrowing...");
    }

}
