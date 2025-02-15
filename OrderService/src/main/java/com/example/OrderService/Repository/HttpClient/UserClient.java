package com.example.OrderService.Repository.HttpClient;

import com.example.OrderService.Dto.Response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;

@FeignClient(name = "user-service",url = "http://localhost:8083/api/userservice")
public interface UserClient {
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse findUserById(@PathVariable("id") String id);

}
