package com.example.OrderService.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "OrderShipper_Tb")
public class OrderShipper {
    @Id
    String id;
    String orderId;
    String shipperId;
    String locationCurrent;
    String locationDelivery;
    LocalDateTime assignedAt;
    String status;
}
