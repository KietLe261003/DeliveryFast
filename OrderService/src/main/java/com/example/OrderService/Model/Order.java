package com.example.OrderService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value = "Order_Tb")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
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
