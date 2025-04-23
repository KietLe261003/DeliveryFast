package com.example.ShipperService.Repository;

import com.example.ShipperService.Model.ApiClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApiClientRepository extends MongoRepository<ApiClient, String> {
    Optional<ApiClient> findByApiKeyAndActiveTrue(String apiKey);
}
