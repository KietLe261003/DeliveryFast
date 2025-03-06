package com.example.ShipperService.Dto.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ApiUserResponse {
    private int code;
    private String message;
    private UserResponse data;
}
