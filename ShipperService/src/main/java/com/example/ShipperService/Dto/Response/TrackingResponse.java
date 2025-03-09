package com.example.ShipperService.Dto.Response;

import com.example.ShipperService.Model.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
public class TrackingResponse {
    @Id
    String id;
    String orderId;
    String status;
    GeoPoint location;
    LocalDateTime timeStamp; //thời gian đơn hàng được cập nhật lần cuối
    @NonFinal
    String ShipperId;
}
