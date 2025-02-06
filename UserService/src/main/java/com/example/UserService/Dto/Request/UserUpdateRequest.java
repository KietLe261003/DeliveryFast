package com.example.UserService.Dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean gender;
    private String address;
    private Date dateOfBirth;
    private String role;
    private String accountStatus;
}
