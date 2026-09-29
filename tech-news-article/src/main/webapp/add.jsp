<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/authcheck.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Tech Article</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 450px; margin: 0 auto; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; color: #555; }
        input[type="text"], textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        input[type="submit"] { background-color: #0066cc; color: white; border: none; padding: 10px 20px; font-size: 15px; font-weight: bold; border-radius: 4px; cursor: pointer; width: 100%; }
        input[type="submit"]:hover { background-color: #0052a3; }
        .error-box { background-color: #ffe6e6; color: #cc0000; border: 1px solid #ff9999; padding: 10px; border-radius: 4px; margin-bottom: 15px; font-size: 14px; }
        .nav-link { display: inline-block; margin-top: 15px; color: #0066cc; text-decoration: none; }
        .nav-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Add Tech Article</h2>

        <% 
            String error = request.getParameter("error");
            if (error != null) { 
        %>
            <div class="error-box">
                <strong>⚠️ Failed to save:</strong> <%= error %>
            </div>
        <% } %>

        <form action="add-article" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required placeholder="Enter article title">
            </div>

            <div class="form-group">
                <label for="content">Content:</label>
                <textarea id="content" name="content" rows="5" required placeholder="Enter article content"></textarea>
            </div>

            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" id="author" name="author" required placeholder="Enter author name">
            </div>

            <input type="submit" value="Submit Article">
        </form>

        <a href="dashboard" class="nav-link">&larr; Back to Dashboard</a> | 
        <a href="articles" class="nav-link">View All Articles</a>
    </div>
<%@ include file="/WEB-INF/jspf/autologout.jspf" %>
</body>
</html>
