package com.technews.servlet;

import com.technews.util.LoggedInUsers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            res.sendRedirect("index.jsp");
            return;
        }

        int visits = 1;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("visitCount".equals(c.getName())) {
                    try { visits = Integer.parseInt(c.getValue()) + 1; } catch (Exception ignore) {}
                }
            }
        }
        Cookie visitCookie = new Cookie("visitCount", String.valueOf(visits));
        visitCookie.setMaxAge(60 * 60 * 24);
        res.addCookie(visitCookie);

        req.setAttribute("visits", visits);
        req.setAttribute("activeUsers", LoggedInUsers.namesList());
        req.getRequestDispatcher("dashboard.jsp").forward(req, res);
    }
}
