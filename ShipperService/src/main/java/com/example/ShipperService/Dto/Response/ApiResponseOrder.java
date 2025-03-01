package com.example.ShipperService.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseOrder {
    private int code;
    private String message;
    private OrderResponse data;
}
