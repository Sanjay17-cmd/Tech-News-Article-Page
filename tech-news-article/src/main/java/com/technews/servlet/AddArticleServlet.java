package com.technews.servlet;

import com.technews.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/add-article")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String author = req.getParameter("author");
        String errorMsg = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                errorMsg = "Could not connect to MySQL Database.";
            } else {
                try (PreparedStatement create = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS articles (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, content TEXT NOT NULL, author VARCHAR(100) NOT NULL)")) {
                    create.executeUpdate();
                }
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO articles (title, content, author) VALUES (?, ?, ?)")) {
                    ps.setString(1, title);
                    ps.setString(2, content);
                    ps.setString(3, author);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }

        if (errorMsg != null) {
            res.sendRedirect("add.jsp?error=" + URLEncoder.encode(errorMsg, "UTF-8"));
        } else {
            res.sendRedirect("articles");
        }
    }
}
