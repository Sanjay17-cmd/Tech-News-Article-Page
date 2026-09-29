<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/authcheck.jspf" %>
<!DOCTYPE html>
<html><head><title>News Subscription</title>
<style>
body { font-family:'Segoe UI',Tahoma,sans-serif; background:#f4f6f8; margin:30px; }
.container { max-width:650px; margin:auto; background:white; padding:25px; border-radius:8px; box-shadow:0 2px 8px #ccc; }
.package { display:inline-block; vertical-align:top; width:26%; margin:1%; padding:14px; background:#f0fdf4; border:1px solid #86efac; }
input, select { display:block; width:95%; padding:10px; margin:10px 0; border:1px solid #ccd3da; border-radius:4px; }
.btn { padding:10px 18px; background:#0066cc; color:white; border:0; border-radius:4px; cursor:pointer; }
.done { padding:15px; background:#dcfce7; border:1px solid #86efac; }
a { color:#0066cc; }
</style></head><body>
<div class="container">
<h2>Tech News Subscription</h2>
<div class="package"><b>Basic</b><br>News only</div>
<div class="package"><b>Pro</b><br>News + analysis</div>
<div class="package"><b>Premium</b><br>All content</div>
<% if ("true".equals(request.getParameter("deleted"))) { %>
    <div class="done">Your subscription was deleted.</div>
<% } %>
<% if (session.getAttribute("subscriptionError") != null) { %>
    <div class="done">Could not delete subscription: <%= session.getAttribute("subscriptionError") %></div>
    <% session.removeAttribute("subscriptionError"); %>
<% } %>
<% if (session.getAttribute("purchaseName") != null) { %>
    <div class="done"><b>Purchase details saved.</b><br>
    Name: <%= session.getAttribute("purchaseName") %><br>
    Email: <%= session.getAttribute("purchaseEmail") %><br>
    Package: <%= session.getAttribute("packageName") %><br>
    Period: <%= session.getAttribute("period") %></div>
    <p><a class="btn" href="delete-subscription">Delete Subscription</a></p>
<% } else { %>
    <form action="purchase" method="post">
        <input name="name" placeholder="Your name" required>
        <input type="email" name="email" placeholder="Your email" required>
        <select name="packageName"><option>Basic</option><option>Pro</option><option>Premium</option></select>
        <select name="period"><option>Monthly</option><option>Yearly</option></select>
        <button class="btn" type="submit">Confirm Subscription</button>
    </form>
<% } %>
<p><a href="dashboard">Back to dashboard</a></p>
</div>
<%@ include file="/WEB-INF/jspf/autologout.jspf" %>
</body></html>
