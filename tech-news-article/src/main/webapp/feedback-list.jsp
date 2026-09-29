<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/authcheck.jspf" %>
<%@ page import="java.util.List" %>
<%@ page import="com.technews.model.Feedback" %>
<!DOCTYPE html>
<html><head><title>Feedback (XML DB)</title>
<style>
body { font-family:'Segoe UI',Tahoma,sans-serif; background:#f4f6f8; margin:30px; }
.container { max-width:880px; margin:auto; background:white; padding:25px; border-radius:8px; box-shadow:0 2px 8px #ccc; }
table { width:100%; border-collapse:collapse; margin-top:15px; }
table, th, td { border:1px solid #ddd; }
th, td { padding:10px; text-align:left; }
th { background:#0066cc; color:white; }
tr:nth-child(even) { background:#f9f9f9; }
.nav a { color:#0066cc; text-decoration:none; font-weight:bold; margin-right:15px; }
.search-box { margin:15px 0; padding:14px; background:#eef5ff; border-radius:6px; }
select, button { padding:8px; border-radius:4px; }
.no-data { padding:15px; background:#fff3cd; color:#856404; border:1px solid #ffeeba; border-radius:5px; }
</style></head><body>
<div class="container">
<h2>Feedback (XML Database)</h2>
<div class="nav">
    <a href="dashboard">Dashboard</a>
    <a href="feedback.jsp">Add Feedback</a>
    <a href="feedback-summary">XSLT Summary</a>
</div>

<div class="search-box">
    <form action="feedbacks" method="get">
        XPath search &mdash; show feedback with rating greater than:
        <select name="minRating">
            <option value="">-- any --</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
        <button type="submit">Search</button>
    </form>
</div>

<%
    String dbError = (String) request.getAttribute("dbError");
    if (dbError != null) {
%>
    <div class="no-data">Error: <%= dbError %></div>
<% } %>

<%
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
    if (feedbacks != null && !feedbacks.isEmpty()) {
%>
    <table>
        <tr><th>Name</th><th>Email</th><th>Subject</th><th>Message</th><th>Rating</th></tr>
        <% for (Feedback f : feedbacks) { %>
        <tr>
            <td><%= f.getName() %></td>
            <td><%= f.getEmail() %></td>
            <td><%= f.getSubject() %></td>
            <td><%= f.getMessage() %></td>
            <td><%= f.getRating() %> / 5</td>
        </tr>
        <% } %>
    </table>
<% } else if (dbError == null) { %>
    <div class="no-data">No feedback found. <a href="feedback.jsp">Add one</a>!</div>
<% } %>
</div>
<%@ include file="/WEB-INF/jspf/autologout.jspf" %>
</body></html>
