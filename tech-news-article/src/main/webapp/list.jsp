<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.technews.model.Article" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tech Articles List</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        .nav-links a { color: #0066cc; text-decoration: none; font-weight: bold; margin-right: 15px; }
        .nav-links a:hover { text-decoration: underline; }
        .error-box { background-color: #ffe6e6; color: #cc0000; border: 1px solid #ff9999; padding: 12px; border-radius: 5px; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        table, th, td { border: 1px solid #dddddd; }
        th, td { padding: 12px; text-align: left; }
        th { background-color: #0066cc; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .no-data { padding: 15px; background: #fff3cd; color: #856404; border: 1px solid #ffeeba; border-radius: 5px; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Tech News Articles</h2>
        <div class="nav-links">
            <a href="dashboard">Dashboard</a> | 
            <a href="add.jsp">Add New Article</a>
        </div>
        <br>

        <% 
            String dbError = (String) request.getAttribute("dbError");
            if (dbError != null) { 
        %>
            <div class="error-box">
                <strong>⚠️ Database / System Error:</strong><br>
                <%= dbError %>
            </div>
        <% } %>

        <%
            List<Article> articles = (List<Article>) request.getAttribute("articles");
            if (articles != null && !articles.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Author</th>
                </tr>
                <% for (Article a : articles) { %>
                <tr>
                    <td><%= a.getId() %></td>
                    <td><b><%= a.getTitle() %></b></td>
                    <td><%= a.getContent() %></td>
                    <td><%= a.getAuthor() %></td>
                </tr>
                <% } %>
            </table>
        <% } else if (dbError == null) { %>
            <div class="no-data">
                No articles found in MySQL table `articles`. <a href="add.jsp">Click here to add one</a>!
            </div>
        <% } %>
    </div>

</body>
</html>
