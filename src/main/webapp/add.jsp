<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add News - Tech News</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 520px; margin: 40px auto; background: white; padding: 28px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        label { display: block; margin-bottom: 6px; font-weight: bold; color: #555; }
        input, textarea { width: 100%; padding: 10px; margin-bottom: 14px; border: 1px solid #ccc; border-radius: 4px; }
        textarea { height: 100px; }
        button, .btn { display: inline-block; padding: 10px 16px; color: white; background-color: #0066cc; text-decoration: none; border: none; border-radius: 4px; cursor: pointer; }
        .btn-secondary { background-color: #4b5563; }
        .error-box { background: #ffe6e6; color: #b91c1c; padding: 10px; border-radius: 4px; margin-bottom: 14px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add News Article</h2>
    <% String error = request.getParameter("error"); if (error != null) { %>
        <div class="error-box"><%= error %></div>
    <% } %>
    <form action="add-article" method="post">
        <label>Title</label>
        <input type="text" name="title" required>
        <label>Content</label>
        <textarea name="content" required></textarea>
        <label>Author</label>
        <input type="text" name="author" required>
        <button type="submit">Save Article</button>
        <a href="index.jsp" class="btn btn-secondary" style="margin-left:8px;">Back Home</a>
    </form>
</div>
</body>
</html>
