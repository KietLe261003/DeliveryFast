package com.example.ShipperService.Repository;

import com.example.ShipperService.Model.Tracking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends MongoRepository<Tracking, String> {
    List<Tracking> findByOrderId(String orderId);
    List<Tracking> getAllTrackingByStatus(String status);
    @Query("{'status': { $in: [?0, ?1,?2] }}")
    List<Tracking> findByStatusInTwoValues(String status1, String status2,String status3);
    List<Tracking> findByShipperId(String shipperId);
    @Query(value = "{'shipperId': ?0, 'orderId': ?1,'status': 'complete'}",exists = true)
    Boolean findByShipperIdAndOrderId(String shipperId, String orderId);
}
