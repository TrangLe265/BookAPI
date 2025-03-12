package com.trangle.bookdb.service;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpHeaders;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATIONTIME = 86400000; 
    //EXPIRATIONTIME: A static final variable representing the expiration time for a JWT (JSON Web Token), set to 86,400,000 milliseconds (1 day). This value can be adjusted for production environments.

    //PREFIX: A static final variable representing the prefix for the JWT, typically used in the Authorization header of HTTP requests.
    static final String PREFIX ="Bearer"; 

    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 

    //generated signed JWT token
    public String getToken(String username){
        String token = Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(key)
        .compact(); 

        return token;
    }

    //get a token from request Authorization Header, verify the token and get the username
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader("Authorization"); 

        if (token != null){
            String user = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token.replace(PREFIX, ""))
            .getBody()
            .getSubject(); 

            if (user != null){
                return user; 
            }
        } 
        
        return null; 

    }


}
