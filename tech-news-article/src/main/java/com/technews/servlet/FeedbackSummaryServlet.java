package com.technews.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

public class FeedbackSummaryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String xmlPath = getServletContext().getRealPath("/WEB-INF/data/feedbacks.xml");
        String xslPath = getServletContext().getRealPath("/WEB-INF/data/feedback.xsl");
        res.setContentType("text/html;charset=UTF-8");

        try {
            Transformer t = TransformerFactory.newInstance().newTransformer(new StreamSource(xslPath));
            t.transform(new StreamSource(xmlPath), new StreamResult(res.getWriter()));
        } catch (Exception e) {
            res.getWriter().println("XSLT transform error: " + e.getMessage());
        }
    }
}
