<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tech News Home</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 500px; margin: 50px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); text-align: center; }
        h2 { color: #333; margin-top: 0; }
        p { color: #666; margin-bottom: 25px; }
        .btn { display: inline-block; padding: 10px 20px; color: white; background-color: #0066cc; text-decoration: none; border-radius: 4px; font-weight: bold; margin: 5px; }
        .btn:hover { background-color: #0052a3; }
        .btn-secondary { background-color: #4b5563; }
        .btn-secondary:hover { background-color: #374151; }
    </style>
</head>
<body>

    <div class="container">
        <h2>⚡ Tech News Portal</h2>
        <p>Welcome to the Tech News Article Page.</p>
        <div style="margin-top: 20px;">
            <a href="articles" class="btn">View All Articles</a>
            <a href="add.jsp" class="btn btn-secondary">Add New Article</a>
        </div>
    </div>

</body>
</html>
