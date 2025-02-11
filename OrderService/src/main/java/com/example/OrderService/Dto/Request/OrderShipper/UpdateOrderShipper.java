package com.example.OrderService.Dto.Request.OrderShipper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateOrderShipper {
    String shipperId;
    String locationCurrent;
    String locationDelivery;
    String status;
}
