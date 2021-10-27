package com.example.demo1.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("id",servletRequest.getParameter("id"));
        servletRequest.setAttribute("firstName",servletRequest.getParameter("firstName"));
        servletRequest.setAttribute("lastName",servletRequest.getParameter("lastName"));
        servletRequest.setAttribute("phone",servletRequest.getParameter("phone"));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
