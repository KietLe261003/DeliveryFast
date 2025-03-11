package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Request.Tracking.CreateTracking;
import com.example.ShipperService.Dto.Request.Tracking.UpdateTracking;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderservice/tracking")
public class TrackingCotroller {
    @Autowired
    TrackingService trackingService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllTracking() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getAllTracking())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getTrackingById(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getTrackingById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getbyorder/{id}")
    public ResponseEntity<ApiResponse> getTrackingByOrderId(@PathVariable String id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getTrackingByOrderId(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> createTracking(@RequestBody CreateTracking createTracking) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.createTracking(createTracking))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/gettrackingbystatus/{status}")
    public ResponseEntity<ApiResponse> getTrackingByStatus(@PathVariable String status) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getTrackingByStatus(status))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/gettrackingbyshipperid/{shipperId}")
    public ResponseEntity<ApiResponse> getTrackingByShipperId(@PathVariable String shipperId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getTrackingByShipperId(shipperId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/deleteByOrderId/{orderId}")
    public ResponseEntity<ApiResponse> deleteTrackingByOrderId(@PathVariable String orderId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.deleteTrackingByOrderId(orderId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTracking(@PathVariable String id, @RequestBody UpdateTracking updateTracking) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.updateTracking(id, updateTracking))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getHistoryShipper/{shipperId}")
    public ResponseEntity<ApiResponse> getHistoryShipper(@PathVariable String shipperId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.getHistoryShipper(shipperId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/checkshipperIdandorderId/{shipperId}/{orderId}")
    public ResponseEntity<ApiResponse> checkShipperIdAndOrderId(@PathVariable String shipperId, @PathVariable String orderId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(trackingService.checkShipperIdAndOrderId(shipperId, orderId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }


}
