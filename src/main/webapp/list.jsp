<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.technews.model.Article" %>
<!DOCTYPE html>
<html>
<head>
    <title>View News - Tech News</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 760px; margin: 40px auto; background: white; padding: 28px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        .card { border: 1px solid #ddd; border-radius: 6px; padding: 14px; margin-bottom: 14px; }
        .btn { display: inline-block; padding: 10px 16px; color: white; background-color: #0066cc; text-decoration: none; border-radius: 4px; margin-bottom: 18px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Latest News</h2>
    <a href="index.jsp" class="btn">Back Home</a>
    <% List<Article> articles = (List<Article>) request.getAttribute("articles"); %>
    <% if (articles != null && !articles.isEmpty()) { %>
        <% for (Article article : articles) { %>
            <div class="card">
                <h3><%= article.getTitle() %></h3>
                <p><%= article.getContent() %></p>
                <small>By <%= article.getAuthor() %></small>
            </div>
        <% } %>
    <% } else { %>
        <p>No articles found yet.</p>
    <% } %>
</div>
</body>
</html>
