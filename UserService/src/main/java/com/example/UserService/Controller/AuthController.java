package com.example.UserService.Controller;

import com.example.UserService.Dto.Request.LoginRequest;
import com.example.UserService.Dto.Request.TokenCheck;
import com.example.UserService.Dto.Response.ApiResponse;
import com.example.UserService.Service.AuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.ws.rs.client.Entity;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/api/userservice/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> Login(@RequestBody LoginRequest loginRequest) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Login Successful")
                .data(authService.Login(loginRequest.getEmail(), loginRequest.getPassword()))
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/checktoken")
    public ResponseEntity<ApiResponse> checkToken(@RequestBody TokenCheck authorization) throws ParseException, JOSEException {
        String token = authorization.getToken();
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Login Successful")
                .data(authService.introspect(token))
                .build();
        return ResponseEntity.ok(response);
    }



}
