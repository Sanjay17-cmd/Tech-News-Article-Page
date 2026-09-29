<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/authcheck.jspf" %>
<!DOCTYPE html>
<html><head><title>Tech News Dashboard</title>
<style>
body { font-family:'Segoe UI',Tahoma,sans-serif; background:#f4f6f8; margin:30px; color:#333; }
.container { max-width:800px; margin:auto; background:white; padding:25px; border-radius:8px; box-shadow:0 2px 8px #ccc; }
.box { display:inline-block; vertical-align:top; width:42%; margin:1%; padding:18px; background:#eef5ff; border-left:4px solid #0066cc; }
.btn { display:inline-block; padding:10px 16px; color:white; background:#0066cc; text-decoration:none; border:0; border-radius:4px; margin:5px 3px; cursor:pointer; }
.danger { background:#b91c1c; } a { color:#0066cc; }
</style></head><body>
<div class="container">
    <h2>Welcome, <%= session.getAttribute("user") %></h2>
    <div class="box">Dashboard visits (cookie): <b id="visits"><%= request.getAttribute("visits") %></b></div>
    <div class="box">Active logged-in sessions: <b id="active"><%= com.technews.util.LoggedInUsers.count() %></b><br>
        <small id="names"><%= request.getAttribute("activeUsers") %></small></div>
    <p>Session expires after 2 minutes idle (client timer + server session-timeout).</p>
    <a class="btn" href="subscription.jsp">Buy News Subscription</a>
    <a class="btn danger" href="delete-subscription">Delete Subscription</a>
    <a class="btn" href="articles">Read Articles</a>
    <a class="btn" href="feedbacks">Feedback (XML DB)</a>
    <a class="btn danger" href="delete-cookie">Delete Visit Cookie</a>
    <a class="btn" href="logout">Logout</a>
</div>
<%@ include file="/WEB-INF/jspf/autologout.jspf" %>
</body></html>
