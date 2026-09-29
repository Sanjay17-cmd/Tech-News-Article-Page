package com.technews.servlet;

import com.technews.util.FeedbackXmlDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FeedbackListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String minRatingParam = req.getParameter("minRating");
        Integer minRating = null;
        if (minRatingParam != null && !minRatingParam.isEmpty()) {
            try { minRating = Integer.parseInt(minRatingParam); } catch (Exception ignore) { }
        }

        String xmlPath = getServletContext().getRealPath("/WEB-INF/data/feedbacks.xml");
        try {
            req.setAttribute("feedbacks", FeedbackXmlDB.search(xmlPath, minRating));
        } catch (Exception e) {
            req.setAttribute("dbError", e.getMessage());
        }
        req.setAttribute("minRating", minRatingParam);
        req.getRequestDispatcher("feedback-list.jsp").forward(req, res);
    }
}
