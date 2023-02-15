package com.mishcma.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mishcma.gradesubmission.exception.EntityNotFoundException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException exception) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username doesn't exists");
            response.getWriter().flush();
        } catch (JWTVerificationException exception) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT not valid");
            response.getWriter().flush();
        }
        catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();
        }
        
    }
    
}
