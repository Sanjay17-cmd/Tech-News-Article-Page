package com.technews.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteSubscriptionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("purchaseName") != null) {
            session.removeAttribute("purchaseName");
            session.removeAttribute("purchaseEmail");
            session.removeAttribute("packageName");
            session.removeAttribute("period");
            res.sendRedirect("subscription.jsp?deleted=true");
        } else {
            session.setAttribute("subscriptionError", "No active subscription found.");
            res.sendRedirect("subscription.jsp");
        }
    }
}
