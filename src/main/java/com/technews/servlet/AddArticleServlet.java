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

        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) {
                errorMsg = "Could not connect to MySQL Database. Check DB credentials in DBConnection.java.";
            } else {
                String sql = "INSERT INTO articles (title, content, author) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setString(3, author);
                
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            e.printStackTrace();
        }

        if (errorMsg != null) {
            // Redirect with error message
            response.sendRedirect("add.jsp?error=" + java.net.URLEncoder.encode(errorMsg, "UTF-8"));
        } else {
            response.sendRedirect("articles");
        }
    }
}
