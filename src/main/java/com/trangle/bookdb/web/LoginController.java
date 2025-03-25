package com.trangle.bookdb.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trangle.bookdb.domain.AccountCredentials;
import com.trangle.bookdb.service.JwtService;



@Controller
public class LoginController {
    private final JwtService jwtService; 
    private final AuthenticationManager authenticationManager; 

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager; 
    }
    
    @GetMapping("/login")
    public String login() {	
        return "login"; 
        //remember that the annotation has to be @Controller instead of ResrController for the thymeleaf view to be returned
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password()); 

        //This line uses the authenticationManager to authenticate the token. If the credentials are valid, it returns an Authentication object.
        Authentication auth = authenticationManager.authenticate(creds); 

        //generate tokens using the authenticated user's name
        String jwts = jwtService.getToken(auth.getName()); 

        //Build response with the genegrated token
        /*return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts)
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
            .build(); */
        
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwts); 
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization"); 

        return ResponseEntity.ok()
            .headers(headers)
            .body("{\"success\": true, \"token\": \"" + jwts + "\", \"redirect\": \"/booklists\"}");
    }
    
}
