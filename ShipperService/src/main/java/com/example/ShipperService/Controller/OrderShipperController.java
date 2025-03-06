package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Request.OrderShipper.CreateOrderShipper;
import com.example.ShipperService.Dto.Request.OrderShipper.UpdateOrderShipper;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Service.OrderShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipperservice/ordershipper")
public class OrderShipperController {
    @Autowired
    OrderShipperService orderShipperService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllOrderShipper() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderShipperById(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.findById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addOrderShipper(@RequestBody CreateOrderShipper createOrderShipper) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.create(createOrderShipper))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOrderShipper(@PathVariable String id, @RequestBody UpdateOrderShipper updateOrderShipper) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.update(id, updateOrderShipper))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrderShipper(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.delete(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/findbyshipperid/{id}")
    public ResponseEntity<ApiResponse> findbyshipperid(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.findByShipperId(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/findbyorderid/{id}")
    public ResponseEntity<ApiResponse> findbyorderid(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(orderShipperService.findByOrderId(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
