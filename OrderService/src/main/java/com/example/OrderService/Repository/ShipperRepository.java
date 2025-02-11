package com.example.OrderService.Repository;

import com.example.OrderService.Model.Shipper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends MongoRepository<Shipper, String> {
}
