package com.example.OrderService.Controller;

import com.example.OrderService.Dto.Request.Order.CreateOrder;
import com.example.OrderService.Dto.Request.Order.UpdateOrder;
import com.example.OrderService.Dto.Response.ApiResponse;
import com.example.OrderService.Model.Order;
import com.example.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse> addOrder(@RequestBody CreateOrder createOrder) {
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
}
