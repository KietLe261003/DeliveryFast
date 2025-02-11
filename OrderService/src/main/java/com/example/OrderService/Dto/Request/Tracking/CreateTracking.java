package com.example.OrderService.Dto.Request.Tracking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTracking {
    String orderId;
    String location;
}
