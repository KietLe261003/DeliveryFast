package com.example.OrderService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "OrderShipper_Tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
