package com.example.UserService.Controller;

import com.example.UserService.Dto.Request.UserRequest;
import com.example.UserService.Dto.Request.UserUpdateRequest;
import com.example.UserService.Dto.Response.ApiResponse;
import com.example.UserService.Dto.Response.UserResponse;
import com.example.UserService.Model.User;
import com.example.UserService.Service.UserService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/userservice")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorization) throws ParseException, JOSEException {
        String token = authorization.substring(7);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.findAll(token))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") String id) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.findById(id))
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest userRequest) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.CreateUser(userRequest))
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest userUpdateRequest) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.updateUser(id, userUpdateRequest))
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.deleteUser(id))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findbytoken/{token}")
    public ResponseEntity<ApiResponse> findByToken(@PathVariable("token") String token) {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.findUserByToken(token))
                .build();
        return ResponseEntity.ok(response);
    }
}
