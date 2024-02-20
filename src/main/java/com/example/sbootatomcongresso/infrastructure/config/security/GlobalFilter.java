package com.example.sbootatomcongresso.infrastructure.config.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("request: {}",String.format("RemoteAddr: {%s}, Content-Type: {%s}, RemotePort: {%s}, " +
                        "RequestId: {%s}, RemoteHost: {%s}, Method: {%s}, RequestURI: {%s}, HeaderAuthorization: {%s}",
                servletRequest.getRemoteAddr(),
                servletRequest.getContentType(),
                servletRequest.getRemotePort(),
                servletRequest.getRequestId(),
                servletRequest.getRemoteHost(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getHeader("Authorization")));
        logger.info("response: {}",String.format("Status: {%s}, Content-Type: {%s}",
                httpServletResponse.getStatus(),
                httpServletResponse.getContentType()));
        //((HttpServletResponse) servletResponse).setHeader("Authorization",httpServletRequest.getHeader("Authorization"));
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
