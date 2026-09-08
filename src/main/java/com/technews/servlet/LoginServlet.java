package com.technews.servlet;

import com.technews.util.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        boolean valid = false;
        String errorMsg = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                errorMsg = "Cannot connect to MySQL. Check DBConnection.java.";
            } else {
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        valid = rs.next();
                    }
                }
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
            e.printStackTrace();
        }

        if (valid) {
            HttpSession session = request.getSession();
            if (session.getAttribute("loggedInUser") == null) {
                session.setAttribute("loggedInUser", email);
                session.setAttribute("userName", name);
                session.setAttribute("userEmail", email);
                session.setMaxInactiveInterval(120);

                Integer activeUsers = (Integer) getServletContext().getAttribute("activeUsers");
                if (activeUsers == null) {
                    activeUsers = 0;
                }
                getServletContext().setAttribute("activeUsers", activeUsers + 1);
            }

            Cookie loginCookie = new Cookie("wasLoggedIn", "true");
            loginCookie.setMaxAge(3600);
            loginCookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
            response.addCookie(loginCookie);
            response.sendRedirect("index.jsp");
        } else {
            if (errorMsg == null) {
                errorMsg = "Invalid email or password.";
            }
            response.sendRedirect("login.jsp?error=" + java.net.URLEncoder.encode(errorMsg, "UTF-8"));
        }
    }
}
