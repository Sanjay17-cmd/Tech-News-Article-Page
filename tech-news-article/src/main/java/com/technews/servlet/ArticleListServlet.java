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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/articles")
public class ArticleListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Article> articles = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                req.setAttribute("dbError", "Could not connect to MySQL Database.");
            } else {
                try (PreparedStatement create = conn.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS articles (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, content TEXT NOT NULL, author VARCHAR(100) NOT NULL)")) {
                    create.executeUpdate();
                }
                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM articles ORDER BY id DESC");
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        articles.add(new Article(rs.getInt("id"), rs.getString("title"),
                                rs.getString("content"), rs.getString("author")));
                    }
                }
            }
        } catch (Exception e) {
            req.setAttribute("dbError", e.getMessage());
        }

        req.setAttribute("articles", articles);
        req.getRequestDispatcher("list.jsp").forward(req, res);
    }
}
