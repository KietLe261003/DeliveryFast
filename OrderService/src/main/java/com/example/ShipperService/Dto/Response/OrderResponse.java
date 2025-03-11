package com.example.ShipperService.Dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    String id;
    InfoUser infoUser;
    String orderCode;
    String senderAddress;
    String reciverName;
    String reciverPhone;
    String receiverAddress;
    String note;
    Integer weight;
    BigDecimal deliveryFee;
    List<String> imageUrls;
    String status;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
