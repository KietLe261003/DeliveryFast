package com.example.ShipperService.Repository.HttpClient;

import com.example.ShipperService.Dto.Response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://USERSERVICE:8083/api/userservice")
public interface UserClient {
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse findUserById(@PathVariable("id") String id);

}
