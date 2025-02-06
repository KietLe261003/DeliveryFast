package com.example.OrderService.Dto.Request.Order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrder {
    String userId;
    String senderAddress;
    String reciverName;
    String reciverPhone;
    String receiverAddress;
    String note;
    Integer weight;
    BigDecimal deliveryFee;
    String status;
}
