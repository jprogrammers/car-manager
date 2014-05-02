package com.jprogrammers.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */
public class SessionCheckerFilter  implements Filter {

    private ArrayList<String> urlList;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();
        boolean allowedRequest = true;

        if(urlList.contains(url)) {
            allowedRequest = false;
        }

        if (!allowedRequest) {
            HttpSession session = request.getSession(true);

            if (null == session || session.getAttribute("loggedIn") == null || session.getAttribute("loggedIn").equals("")) {
                response.sendRedirect("/");
            }
        }


        filterChain.doFilter(servletRequest , servletResponse);
    }

    @Override
    public void destroy() {

    }
}
