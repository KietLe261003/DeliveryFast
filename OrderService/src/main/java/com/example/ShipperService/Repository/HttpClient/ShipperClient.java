package com.example.ShipperService.Repository.HttpClient;

import com.example.ShipperService.Dto.Response.Shipper.ApiResponseShipper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shipper-service",url = "http://ShipperService:8084/api/shipperservice")
public interface ShipperClient {
    @GetMapping(value = "/shipper/findByUserId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponseShipper findShipperByUserId(@PathVariable String id);
}
