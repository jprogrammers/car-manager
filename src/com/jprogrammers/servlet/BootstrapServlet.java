package com.jprogrammers.servlet;

import com.jprogrammers.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Ali Reza Akbarian
 *         created on 18/04/14.
 */

/**
 * Bootstrap application servlet for initializing server
 */
public class BootstrapServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

        HibernateUtil.buildSessionFactory();

        super.init();
    }
}
