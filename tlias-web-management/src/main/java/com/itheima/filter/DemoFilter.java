package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    /**
     * 初始化方法, 在服务器启动时执行一次
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法 init");
    }

    /**
     * 执行方法, 每次请求时执行一次
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        log.info("执行方法 doFilter");

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    /**
     * 销毁方法, 在服务器关闭时执行一次
     */
    @Override
    public void destroy() {
        log.info("销毁方法 destroy");
        //Filter.super.destroy();
    }
}
