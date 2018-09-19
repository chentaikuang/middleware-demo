package com.xiaochen.middleware.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(filterName = "MyTestFilter")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("===========init=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("==========doFilter==========");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("==========destroy==========");
    }
}
