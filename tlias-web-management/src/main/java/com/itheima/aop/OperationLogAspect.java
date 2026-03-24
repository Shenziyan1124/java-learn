package com.itheima.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.anno.Log;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 创建 ObjectMapper 实例
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 定义切入点：匹配带有 @Log 注解的方法
    @Pointcut("@annotation(com.itheima.anno.Log)")
    private void pt() {
    }

    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取目标类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        // 获取方法参数并直接序列化为 JSON
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 获取返回值
        String returnValue = result != null ? result.toString() : "void";

        // 计算执行时间
        long costTime = System.currentTimeMillis() - startTime;

        // 获取请求对象，用于获取操作人信息
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Integer operateEmpId = null;
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            operateEmpId = getOperatorId(request);
        }

        // 创建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        // 调试日志
        log.info("操作日志 - 类名:{}, 方法名:{}, 参数:{}, 返回值:{}, 耗时:{}ms",
                className, methodName, methodParams, returnValue, costTime);

        // 异步保存日志（避免影响主业务）
        saveLogAsync(operateLog);

        return result;
    }


    /**
     * 从请求头中获取 JWT token，并解析出操作人 ID
     */
    private Integer getOperatorId(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (token != null && !token.isEmpty()) {
                Claims claims = JwtUtils.parseToken(token);
                return claims.get("id", Integer.class);
            }
        } catch (Exception e) {
            log.warn("解析 token 失败：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 异步保存日志到数据库
     */
    private void saveLogAsync(OperateLog operateLog) {
        new Thread(() -> {
            try {
                operateLogMapper.insert(operateLog);
                log.info("操作日志保存成功：{}.{} - 耗时：{}ms",
                        operateLog.getClassName(),
                        operateLog.getMethodName(),
                        operateLog.getCostTime());
            } catch (Exception e) {
                log.error("保存操作日志失败：{}", e.getMessage(), e);
            }
        }).start();
    }
}
