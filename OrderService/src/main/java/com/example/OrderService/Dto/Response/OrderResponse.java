package com.example.OrderService.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {
    String id;
    String userId;
    String orderCode;
    String senderAddress;
    String reciverName;
    String reciverPhone;
    String receiverAddress;
    String note;
    Integer weight;
    BigDecimal deliveryFee;
    String status;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
