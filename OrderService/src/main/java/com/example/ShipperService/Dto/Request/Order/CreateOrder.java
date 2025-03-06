package com.example.ShipperService.Dto.Request.Order;

import com.example.ShipperService.Model.GeoPoint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

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
    private List<MultipartFile> images;
    String status;
    GeoPoint locationSender;
    GeoPoint locationReciver;
}
