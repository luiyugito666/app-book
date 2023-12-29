package com.example.demo2.controller;

import com.example.demo2.request.AuthRequest;
import com.example.demo2.response.TokenResponse;
import com.example.demo2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TokenController {

    @Autowired
   private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtService jwtService;



    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUser(),request.getPassword()));


        final UserDetails userDetails =userDetailsService.loadUserByUsername(request.getUser());

        final String jwt=jwtService.generateToken(userDetails);


        return ResponseEntity.ok(new TokenResponse(jwt));


    }

}
