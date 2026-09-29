package com.technews.servlet;

import com.technews.util.DBConnection;
import com.technews.util.LoggedInUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = null;
        String errorMsg = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                errorMsg = "Cannot connect to MySQL. Check DBConnection.java.";
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT name FROM users WHERE email=? AND password=?")) {
                    ps.setString(1, email);
                    ps.setString(2, password);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) name = rs.getString("name");
                    }
                }
            }
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }

        if (name != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);
            LoggedInUsers.add(name);
            res.sendRedirect("dashboard");
        } else {
            req.setAttribute("error", errorMsg != null ? errorMsg : "Invalid email or password.");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}
