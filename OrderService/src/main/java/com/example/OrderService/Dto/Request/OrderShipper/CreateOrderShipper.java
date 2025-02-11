package com.example.OrderService.Dto.Request.OrderShipper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderShipper {
    String orderId;
    String shipperId;
    String locationCurrent;
}
