package com.example.ShipperService.Repository;

import com.example.ShipperService.Model.OrderShipper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderShipperRepository extends MongoRepository<OrderShipper, String> {
    List<OrderShipper> findByShipperId(String id);
    List<OrderShipper> findByOrderId(String id);
}
