package com.example.ShipperService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "Shipper_Tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    @Id
    String id;
    String name;
    String phoneNumber;
    String email;
    String address;
    Vehicle vehicle;
    boolean status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
