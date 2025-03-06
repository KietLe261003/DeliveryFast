package com.example.ShipperService.Repository.HttpClient;

import com.example.ShipperService.Dto.Request.UserRequest;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Dto.Response.ApiUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",url = "http://USERSERVICE:8083/api/userservice")
public interface UserClient {
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse findUserById(@PathVariable("id") String id);

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiUserResponse createUser(@RequestBody UserRequest userRequest);
}
