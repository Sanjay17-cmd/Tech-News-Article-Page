<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/authcheck.jspf" %>
<!DOCTYPE html>
<html><head><title>Add Feedback</title>
<style>
body { font-family:'Segoe UI',Tahoma,sans-serif; background:#f4f6f8; margin:30px; }
.container { max-width:480px; margin:auto; background:white; padding:25px; border-radius:8px; box-shadow:0 2px 8px #ccc; }
label { display:block; font-weight:bold; margin-bottom:5px; color:#555; }
input, textarea, select { width:100%; padding:10px; margin-bottom:14px; border:1px solid #ccc; border-radius:4px; box-sizing:border-box; }
textarea { height:80px; }
button { background:#0066cc; color:white; border:0; padding:10px 18px; border-radius:4px; cursor:pointer; font-weight:bold; width:100%; }
.error-box { background:#ffe6e6; color:#cc0000; border:1px solid #ff9999; padding:10px; border-radius:4px; margin-bottom:14px; }
a { color:#0066cc; }
</style></head><body>
<div class="container">
<h2>Give Feedback</h2>
<% if (request.getAttribute("error") != null) { %>
    <div class="error-box"><%= request.getAttribute("error") %></div>
<% } %>
<form action="add-feedback" method="post">
    <label>Name</label>
    <input type="text" name="name" required>

    <label>Email</label>
    <input type="email" name="email" required>

    <label>Subject</label>
    <input type="text" name="subject" required>

    <label>Message</label>
    <textarea name="message" required></textarea>

    <label>Rating</label>
    <select name="rating">
        <option value="1">1 - Poor</option>
        <option value="2">2 - Fair</option>
        <option value="3">3 - Good</option>
        <option value="4">4 - Very Good</option>
        <option value="5">5 - Excellent</option>
    </select>

    <button type="submit">Submit Feedback</button>
</form>
<p><a href="feedbacks">&larr; Back to Feedback List</a></p>
</div>
<%@ include file="/WEB-INF/jspf/autologout.jspf" %>
</body></html>
