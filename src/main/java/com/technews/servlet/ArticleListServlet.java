package com.technews.servlet;

import com.technews.model.Article;
import com.technews.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/articles")
public class ArticleListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Article> list = new ArrayList<>();
        String errorMsg = null;
        
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) {
                errorMsg = "Database Connection Failed! Check MySQL server, username/password in DBConnection.java, or missing MySQL Connector JAR in WEB-INF/lib.";
            } else {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM articles");

                while (rs.next()) {
                    Article a = new Article(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author")
                    );
                    list.add(a);
                }
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            errorMsg = "SQL Error: " + e.getMessage();
            e.printStackTrace();
        }

        request.setAttribute("dbError", errorMsg);
        request.setAttribute("articles", list);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
