package com.example.ShipperService.Service;

import com.example.ShipperService.Model.ApiClient;
import com.example.ShipperService.Repository.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApiKeyService {
    @Autowired
    private ApiClientRepository apiClientRepository;

    public String generateApiKey(String clientName) {
        String apiKey = UUID.randomUUID().toString();
        ApiClient client = new ApiClient();
        client.setClientName(clientName);
        client.setApiKey(apiKey);
        client.setActive(true);
        apiClientRepository.save(client);
        return apiKey;
    }

    public boolean isValid(String apiKey) {
        return apiClientRepository.findByApiKeyAndActiveTrue(apiKey).isPresent();
    }
}
