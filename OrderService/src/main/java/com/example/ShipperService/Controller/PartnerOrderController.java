package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Request.Order.CreateOrder;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Service.ApiKeyService;
import com.example.ShipperService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/partner/orders")
public class PartnerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ApiKeyService apiKeyService;

    private boolean isAuthorized(String apiKey) {
        return apiKeyService.isValid(apiKey);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createOrderFromPartner(
            @RequestHeader("X-API-KEY") String apiKey,
            @ModelAttribute CreateOrder createOrder
    ) throws IOException {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .code(403)
                    .message("Invalid API Key")
                    .build());
        }

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Order Created Successfully")
                .data(orderService.create(createOrder))
                .build());
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse> createOrderListFromPartner(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody List<CreateOrder> createOrderList
    ) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(403).body(ApiResponse.builder()
                    .code(403)
                    .message("Invalid API Key")
                    .build());
        }

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Order List Created Successfully")
                .data(orderService.CreateListOrder(createOrderList))
                .build());
    }
}
