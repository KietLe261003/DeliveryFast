package com.example.ShipperService.Dto.Request.OrderShipper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderShipper {
    String shipperId;
    String locationCurrent;
    String locationDelivery;
    String status;
}
