package com.pilon.example.item;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class LoggingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        Assert.isTrue(request instanceof HttpServletRequest, "Expecting request to be instanceof HttpServletRequest");
        HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request);
        String uri = httpServletRequest.getRequestURI();
        logger.debug("Request URI: " + uri);

        chain.doFilter(request, response);
    }
}
