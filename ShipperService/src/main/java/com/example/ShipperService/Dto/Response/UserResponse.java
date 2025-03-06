package com.example.ShipperService.Dto.Response;

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
