package com.example.ShipperService.Repository;

import com.example.ShipperService.Model.Shipper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends MongoRepository<Shipper, String> {
}
