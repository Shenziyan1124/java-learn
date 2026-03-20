package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        log.info("执行方法 doFilter");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //1.获取请求路径
        String requestURI = request.getRequestURI();// /login

        //2.判断是否为登录请求,如果包含/login,则放行,
        if (requestURI.contains("login")) {
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求中的token
        String token = request.getHeader("token");

        //4.判断token是否存在,不存在则没有登录,返回错误信息401
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            //response.getWriter().write("用户未登录");
            return;
        }

        //5.如果token 存在,校验令牌,失败返回错误信息401
        try {
            JwtUtils.parseToken(token);
        }catch (Exception e){
            response.setStatus(401);
            return;
        }

        //6.校验通过,放行
        log.info("令牌校验通过,放行");
        filterChain.doFilter(request, response);

    }
}
