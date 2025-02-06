package com.example.UserService.Dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean gender;
    private String password;
    private String role;
}
