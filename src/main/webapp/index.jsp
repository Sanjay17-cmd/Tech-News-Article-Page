<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ page import="javax.servlet.http.Cookie, javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    String userName = null;
    String userEmail = null;
    if (session != null) {
        userName = (String) session.getAttribute("userName");
        userEmail = (String) session.getAttribute("userEmail");
    }

    int visitCount = 1;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("visitCount".equals(c.getName())) {
                try {
                    visitCount = Integer.parseInt(c.getValue()) + 1;
                } catch (NumberFormatException e) {
                    visitCount = 1;
                }
            }
        }
    }

    Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
    visitCookie.setMaxAge(60 * 60 * 24);
    visitCookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
    response.addCookie(visitCookie);

    Integer activeUsers = (Integer) application.getAttribute("activeUsers");
    if (activeUsers == null) {
        activeUsers = 0;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Tech News Session Cookies</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f4f6f8; margin: 30px; }
        .container { max-width: 520px; margin: 40px auto; background: white; padding: 28px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { color: #333; margin-top: 0; }
        p { color: #555; margin-bottom: 18px; line-height: 1.5; }
        .btn { display: inline-block; padding: 10px 18px; color: white; background-color: #0066cc; text-decoration: none; border-radius: 4px; font-weight: bold; margin: 5px 4px; }
        .btn:hover { background-color: #0052a3; }
        .btn-secondary { background-color: #4b5563; }
        .btn-secondary:hover { background-color: #374151; }
        .info-box { background: #eef6ff; border: 1px solid #cfe2ff; color: #1d4ed8; padding: 14px; border-radius: 6px; margin-bottom: 18px; }
        .small { color: #6b7280; font-size: 14px; }
        .form-inline { display: inline-block; margin-top: 12px; }
    </style>
</head>
<body>

    <div class="container">
        <h2>⚡ Tech News Article Page</h2>

        <% if (userName != null) { %>
            <div class="info-box">
                <strong>Logged in as:</strong> <%= userName %> (<%= userEmail %>)<br>
                <span class="small">Session will expire after 2 minutes of inactivity.</span>
            </div>
        <% } else { %>
            <div class="info-box">
                Hello! Please <a href="login.jsp">login</a> to store your data in session.
            </div>
        <% } %>

        <p>Page visits from cookie: <strong><%= visitCount %></strong></p>
        <p>Active logged-in users: <strong><%= activeUsers %></strong></p>

        <form action="delete-cookie" method="post" class="form-inline">
            <input type="hidden" name="cookieName" value="visitCount">
            <button type="submit" class="btn btn-secondary">Delete Visit Cookie</button>
        </form>

        <div style="margin-top: 24px;">
            <a href="index.jsp" class="btn">Refresh Home</a>
            <a href="articles" class="btn">View News</a>
            <a href="add.jsp" class="btn btn-secondary">Add News</a>
            <a href="logout" class="btn btn-secondary">Log Out</a>
        </div>

        <p class="small" id="timeoutMessage" style="display:none; margin-top:20px; color:#b91c1c; font-weight:bold;">Your session has timed out after 2 minutes of inactivity.</p>
    </div>

    <script>
        var timeoutId;
        function resetTimer() {
            var msg = document.getElementById('timeoutMessage');
            if (msg) msg.style.display = 'none';
            clearTimeout(timeoutId);
            timeoutId = setTimeout(function() {
                document.getElementById('timeoutMessage').style.display = 'block';
            }, 120000);
        }
        document.addEventListener('mousemove', resetTimer);
        document.addEventListener('keydown', resetTimer);
        document.addEventListener('click', resetTimer);
        resetTimer();
    </script>

</body>
</html>
