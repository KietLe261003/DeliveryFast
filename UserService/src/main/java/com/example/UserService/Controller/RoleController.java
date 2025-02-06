package com.example.UserService.Controller;

import com.example.UserService.Dto.Response.ApiResponse;
import com.example.UserService.Model.Role;
import com.example.UserService.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userservice/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllRoles() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(roleService.findAll())
                .build();
        return ResponseEntity.ok(response);
    }
}
