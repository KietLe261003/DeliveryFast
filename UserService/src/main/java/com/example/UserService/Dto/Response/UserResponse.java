package com.example.UserService.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
}
