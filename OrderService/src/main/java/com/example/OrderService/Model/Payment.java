package com.example.OrderService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "Payment_Tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private String id;
    private String orderId;
    private String paymentMethod;
    private String paymentAmount;
    private String paymentStatus;
    private LocalDateTime paymentDate;
}
