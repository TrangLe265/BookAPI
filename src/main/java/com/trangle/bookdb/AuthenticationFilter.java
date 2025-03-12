package com.trangle.bookdb;

import java.net.http.HttpHeaders;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trangle.bookdb.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService; 

    public AuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService; 
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException{
        String jwt= request.getHeader("Authorization"); 

        if (jwt != null){
            //verify token and get user
            String user = jwtService.getAuthUser(request); 
            
            //authenticate
            Authentication authentication = new UsernamePasswordAuthenticationToken( user, null, java.util.Collections.emptyList()); 

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
