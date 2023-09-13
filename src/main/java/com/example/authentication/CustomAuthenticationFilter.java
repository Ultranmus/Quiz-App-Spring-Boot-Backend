package com.example.authentication;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter implements Filter {
    private static final String X_API_KEY="X-API-KEY";
    private static final String X_API_KEY_VALUE="6a93cf040ce0dbf9d91e197171c4bbf0";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(
        ServletRequest request, 
        ServletResponse response, 
        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
        String apiKey = req.getHeader(X_API_KEY);
        
        if (apiKey == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        if (apiKey.equals(X_API_KEY_VALUE)) {
            
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}