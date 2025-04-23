package com.example.ShipperService.Controller;

import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apikey")
public class ApiKeyController {
    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse> generateApiKey(@RequestParam String clientName) {
        String apiKey = apiKeyService.generateApiKey(clientName);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("API Key Generated")
                .data(apiKey)
                .build());
    }
}
