package com.example.ShipperService.Repository.HttpClient;

import com.example.ShipperService.Dto.Response.ApiResponseOrder;
import com.example.ShipperService.Dto.Response.ApiResponseTracking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service",url = "http://localhost:8082/api/orderservice")
public interface OrderClient {
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponseOrder findOrderById(@PathVariable("id") String id);

    @GetMapping(value = "/tracking/gettrackingbystatus/{status}")
    ApiResponseTracking findTrackingByStatus(@PathVariable("status") String status);
}
