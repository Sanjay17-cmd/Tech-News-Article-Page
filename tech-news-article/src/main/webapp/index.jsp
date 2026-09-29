<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tech News Login</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 500px; margin: 50px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); text-align: center; }
        h2 { color: #333; margin-top: 0; }
        p { color: #666; margin-bottom: 25px; }
        .btn { display: inline-block; padding: 10px 20px; color: white; background-color: #0066cc; text-decoration: none; border-radius: 4px; font-weight: bold; margin: 5px; }
        .btn:hover { background-color: #0052a3; }
        .btn-secondary { background-color: #4b5563; }
        .btn-secondary:hover { background-color: #374151; }
        input { display: block; width: 94%; padding: 10px; margin: 10px 0; border: 1px solid #ccd3da; border-radius: 4px; }
        .message { padding: 10px; color: #9a3412; background: #ffedd5; margin-bottom: 15px; }
    </style>
</head>
<body>

    <div class="container">
        <% if ("true".equals(request.getParameter("timeout"))) { %>
            <div class="message">Your session timed out after 2 minutes of inactivity. Please log in again.</div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="message"><%= request.getAttribute("error") %></div>
        <% } %>
        <h2>Tech News Portal</h2>
        <p>Login to read news and manage your subscription.</p>
        <form action="login" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button class="btn" type="submit">Login</button>
        </form>
        <p><a href="articles">View public articles</a></p>
    </div>

</body>
</html>
