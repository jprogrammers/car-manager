package com.jprogrammers.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vahid Forghani
 */

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean isLogin = (request.getSession().getAttribute("login") != null) ? (Boolean)request.getSession().getAttribute("login") : false;

        String requestUrl = request.getRequestURI();
        if(isLogin || requestUrl.contains("/login.xhtml") || requestUrl.contains("/javax.faces.resource/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/page/login.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
