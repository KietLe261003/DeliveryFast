package com.example.OrderService.Dto.Request.Shipper;

import com.example.OrderService.Model.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShipper {
    String name;
    String phoneNumber;
    String email;
    String address;
    Vehicle vehicle;
}
