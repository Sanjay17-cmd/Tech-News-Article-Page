package com.technews.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
    public void init(FilterConfig cfg) { }
    public void destroy() { }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse r = (HttpServletResponse) res;
        r.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        r.setHeader("Pragma", "no-cache");
        r.setDateHeader("Expires", 0);
        chain.doFilter(req, res);
    }
}
