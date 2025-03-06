package com.example.ShipperService.Dto.Request.Shipper;

import com.example.ShipperService.Model.GeoPoint;
import com.example.ShipperService.Model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateShipper {
    String fullName;
    String phoneNumber;
    String password;
    Boolean gender;
    String email;
    String address;
    Vehicle vehicle;
    List<GeoPoint> shipperArea;
}
