package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.Tracking.CreateTracking;
import com.example.ShipperService.Mapper.TrackingMapper;
import com.example.ShipperService.Model.Tracking;
import com.example.ShipperService.Repository.OrderRepository;
import com.example.ShipperService.Repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TrackingMapper trackingMapper;

    public List<Tracking> getAllTracking(){
        return trackingRepository.findAll();
    }
    public Tracking getTrackingById(String trackingId){
        return trackingRepository.findById(trackingId).orElseThrow(()-> new AppException(ErrorCode.NotFoundTracking));
    }
    public Tracking createTracking(CreateTracking createTracking){
        Tracking tracking = trackingMapper.toTracking(createTracking);
        if(!orderRepository.findById(createTracking.getOrderId()).isPresent()){
            new AppException(ErrorCode.NotFoundOrder);
        }
        tracking.setStatus("Running");
        tracking.setTimeStamp(LocalDateTime.now());
        return trackingRepository.save(tracking);
    }
    public List<Tracking> getTrackingByOrderId(String orderId){
        return trackingRepository.findByOrderId(orderId);
    }

}
