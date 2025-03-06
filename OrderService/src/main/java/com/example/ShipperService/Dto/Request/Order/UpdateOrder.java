package com.example.ShipperService.Dto.Request.Order;

import com.example.ShipperService.Model.GeoPoint;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UpdateOrder {
    String senderAddress;
    String reciverName;
    String reciverPhone;
    String receiverAddress;
    String note;
    Integer weight;
    BigDecimal deliveryFee;
    String status;
    GeoPoint locationSender;
    GeoPoint locationReciver;
}
