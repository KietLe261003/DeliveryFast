package com.example.ShipperService.Dto.Response.Shipper;

import com.example.ShipperService.Model.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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
    boolean status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<GeoPoint> shipperArea;
}
