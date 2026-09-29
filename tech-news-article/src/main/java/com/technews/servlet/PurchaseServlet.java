package com.technews.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("purchaseName", req.getParameter("name"));
        session.setAttribute("purchaseEmail", req.getParameter("email"));
        session.setAttribute("packageName", req.getParameter("packageName"));
        session.setAttribute("period", req.getParameter("period"));
        res.sendRedirect("subscription.jsp");
    }
}
