package com.portfolio.jms.Security.jwt;

import com.sun.org.slf4j.internal.Logger;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;


@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Falló el metodo commence ");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
