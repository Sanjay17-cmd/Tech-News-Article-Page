package com.technews.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.technews.util.LoggedInUsers;

public class UserSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        // nothing to do on create
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Object user = se.getSession().getAttribute("user");
        if (user != null) {
            LoggedInUsers.remove((String) user);
        }
    }
}
