package com.technews.servlet;

import com.technews.util.FeedbackXmlDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");
        int rating = Integer.parseInt(req.getParameter("rating"));

        String xmlPath = getServletContext().getRealPath("/WEB-INF/data/feedbacks.xml");
        String xsdPath = getServletContext().getRealPath("/WEB-INF/data/feedback.xsd");

        try {
            FeedbackXmlDB.addFeedback(xmlPath, xsdPath, name, email, subject, message, rating);
            res.sendRedirect("feedbacks");
        } catch (Exception e) {
            req.setAttribute("error", "Rejected by XML schema: " + e.getMessage());
            req.getRequestDispatcher("feedback.jsp").forward(req, res);
        }
    }
}
