package com.technews.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Cookie cookie = new Cookie("visitCount", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        res.sendRedirect("dashboard");
    }
}
