package com.example.ShipperService.Repository;

import com.example.ShipperService.Model.WareHouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WareHouseRepository extends MongoRepository<WareHouse, String> {
    List<WareHouse> findByType(String type);
}
