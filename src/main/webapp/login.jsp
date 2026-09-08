<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Tech News Session Cookies</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 420px; margin: 40px auto; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 6px; color: #555; }
        input[type="text"], input[type="email"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
        input[type="submit"] { background-color: #0066cc; color: white; border: none; padding: 10px 18px; font-size: 15px; border-radius: 4px; cursor: pointer; width: 100%; }
        input[type="submit"]:hover { background-color: #0052a3; }
        .error-box { background-color: #ffe6e6; color: #cc0000; border: 1px solid #ff9999; padding: 10px; border-radius: 4px; margin-bottom: 15px; }
        .nav-link { display: inline-block; margin-top: 15px; color: #0066cc; text-decoration: none; }
        .nav-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Login</h2>

        <% String error = request.getParameter("error"); if (error != null) { %>
            <div class="error-box"><strong>Login failed:</strong> <%= error %></div>
        <% } %>

        <form action="login" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" required placeholder="Your name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required placeholder="you@example.com">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required placeholder="Password">
            </div>
            <input type="submit" value="Login">
        </form>

        <a href="index.jsp" class="nav-link">Back to Home</a>
    </div>

</body>
</html>
