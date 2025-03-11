package com.example.ShipperService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "Tracking_Tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
    @Id
    String id;
    String orderId;
    String description;
    String status;
    GeoPoint location;
    GeoPoint nextLocation;
    LocalDateTime timeStamp; //thời gian đơn hàng được cập nhật lần cuối
    LocalDateTime updateTimeStamp;
    String shipperId;
}
