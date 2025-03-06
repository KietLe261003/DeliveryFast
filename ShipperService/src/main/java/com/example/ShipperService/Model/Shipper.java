package com.example.ShipperService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(value = "Shipper_Tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    @Id
    String id;
    String userId;
    String fullName;
    String email;
    String phoneNumber;
    Boolean gender;
    String address;
    Vehicle vehicle;
    boolean status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<GeoPoint> shipperArea;
}
