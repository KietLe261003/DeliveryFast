package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Request.Order.CreateOrder;
import com.example.ShipperService.Dto.Request.Order.UpdateOrder;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/orderservice")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllOrder() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.findById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addOrder(@ModelAttribute CreateOrder createOrder) throws IOException {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.create(createOrder))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable String id ,@RequestBody UpdateOrder updateOrder) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.update(id, updateOrder))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.delete(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/createlist")
    public ResponseEntity<ApiResponse> createList(@ModelAttribute List<CreateOrder> createOrderList) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderService.CreateListOrder(createOrderList))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
