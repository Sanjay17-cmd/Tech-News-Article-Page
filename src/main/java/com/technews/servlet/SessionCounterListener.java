package com.technews.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounterListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // not used for now
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Object user = se.getSession().getAttribute("loggedInUser");
        if (user != null) {
            ServletContext ctx = se.getSession().getServletContext();
            Integer activeUsers = (Integer) ctx.getAttribute("activeUsers");
            if (activeUsers == null || activeUsers <= 0) {
                activeUsers = 1;
            }
            ctx.setAttribute("activeUsers", activeUsers - 1);
        }
    }
}
