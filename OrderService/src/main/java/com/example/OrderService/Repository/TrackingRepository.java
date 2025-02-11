package com.example.OrderService.Repository;

import com.example.OrderService.Model.Tracking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends MongoRepository<Tracking, String> {
    List<Tracking> findByOrderId(String orderId);
}
