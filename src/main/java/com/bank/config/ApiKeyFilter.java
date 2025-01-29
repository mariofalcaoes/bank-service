package com.bank.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String VALID_API_KEY = "secreta_empresa";

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                    jakarta.servlet.http.HttpServletResponse response,
                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

        String apiKey = request.getHeader(API_KEY_HEADER);
        if (request.getServletPath().contains("webhook")
                && (apiKey == null || !apiKey.equals(VALID_API_KEY))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("API Key invalid");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
