package com.example.ShipperService.Dto.Request.Shipper;

import com.example.ShipperService.Model.Vehicle;
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
