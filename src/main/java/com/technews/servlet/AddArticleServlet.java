package com.technews.servlet;

import com.technews.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/add-article")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        String errorMsg = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                errorMsg = "Could not connect to MySQL Database.";
            } else {
                try (PreparedStatement createStmt = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS articles (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, content TEXT NOT NULL, author VARCHAR(100) NOT NULL)")) {
                    createStmt.executeUpdate();
                }

                String sql = "INSERT INTO articles (title, content, author) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, title);
                    pstmt.setString(2, content);
                    pstmt.setString(3, author);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            e.printStackTrace();
        }

        if (errorMsg != null) {
            response.sendRedirect("add.jsp?error=" + java.net.URLEncoder.encode(errorMsg, "UTF-8"));
        } else {
            response.sendRedirect("articles");
        }
    }
}
