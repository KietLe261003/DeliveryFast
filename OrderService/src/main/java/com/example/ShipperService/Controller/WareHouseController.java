package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Request.WareHouse.WareHouseRequest;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Model.WareHouse;
import com.example.ShipperService.Service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderservice/warehouse")
public class WareHouseController {
    @Autowired
    private WareHouseService wareHouseService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllWarehouses() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.getAllWareHouse())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getWarehouseById(@PathVariable("id") String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.getWareHouseById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/savelist")
    public ResponseEntity<ApiResponse> addWarehouse(@RequestBody List<WareHouseRequest> wareHouseRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.addListWareHouse(wareHouseRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> createWareHouse(@RequestBody WareHouseRequest wareHouseRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.addWareHouse(wareHouseRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateWareHouse(@PathVariable String id, @RequestBody WareHouseRequest wareHouseRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.updateWareHouse(id, wareHouseRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWareHouse(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(wareHouseService.deleteWareHouse(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
