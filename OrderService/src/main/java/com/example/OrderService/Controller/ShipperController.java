package com.example.OrderService.Controller;

import com.example.OrderService.Dto.Request.Shipper.CreateShipper;
import com.example.OrderService.Dto.Response.ApiResponse;
import com.example.OrderService.Model.Shipper;
import com.example.OrderService.Service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderservice/shipper")
public class ShipperController {
    @Autowired
    private ShipperService shipperService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllShipper() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(shipperService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getShipperById(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(shipperService.findById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addShipper(@RequestBody CreateShipper createShipper) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(shipperService.create(createShipper))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateShipper(@PathVariable String id, @RequestBody CreateShipper createShipper) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(shipperService.update(id, createShipper))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteShipper(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(shipperService.delete(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
