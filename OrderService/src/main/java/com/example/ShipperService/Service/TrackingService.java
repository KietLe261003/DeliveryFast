package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.Tracking.CreateTracking;
import com.example.ShipperService.Dto.Request.Tracking.UpdateTracking;
import com.example.ShipperService.Dto.Response.Shipper.ApiResponseShipper;
import com.example.ShipperService.Dto.Response.Shipper.Shipper;
import com.example.ShipperService.Mapper.TrackingMapper;
import com.example.ShipperService.Model.Tracking;
import com.example.ShipperService.Repository.HttpClient.ShipperClient;
import com.example.ShipperService.Repository.OrderRepository;
import com.example.ShipperService.Repository.TrackingRepository;
import com.example.ShipperService.Until.FindTrackingInsideShipperArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackingService {
    private static final Logger log = LoggerFactory.getLogger(TrackingService.class);
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TrackingMapper trackingMapper;

    @Autowired
    ShipperClient shipperClient;

    @Autowired
    FindTrackingInsideShipperArea findTrackingInsideShipperArea;

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
    public List<Tracking> getTrackingByStatus(String status){
        return trackingRepository.getAllTrackingByStatus(status);
    }

    public List<Tracking> getTrackingByShipperId (String shipperId){
        ApiResponseShipper shipperResponse = shipperClient.findShipperByUserId(shipperId);
        log.info(shipperResponse.toString());
        List<Tracking> trackingList = trackingRepository.findByStatusInTwoValues("ready","pending","shipping");

        List<Tracking> filteredTrackingList = findTrackingInsideShipperArea.findTrackingsInShipperArea(shipperResponse.getData().getShipperArea(), trackingList,shipperId);
        return filteredTrackingList;
    }
    public List<Tracking> getHistoryShipper(String shipperId){
        List<Tracking> trackingList = trackingRepository.findByShipperId(shipperId);
        trackingList.stream()
                .filter((item)->item.getStatus().equals("complete"))
                .sorted((item1, item2) -> item1.getUpdateTimeStamp().compareTo(item2.getUpdateTimeStamp()));
        return trackingList;
    }
    public Tracking updateTracking(String trackingId, UpdateTracking updateTracking){
        Tracking tracking = getTrackingById(trackingId);
        tracking.setStatus(updateTracking.getStatus());
        tracking.setShipperId(updateTracking.getShipperId());
        tracking.setUpdateTimeStamp(LocalDateTime.now());
        trackingRepository.save(tracking);
        return tracking;
    }
    public String deleteTrackingByOrderId(String orderId){
        List<Tracking> trackingList = getTrackingByOrderId(orderId);
        trackingRepository.deleteAll(trackingList);
        return "success";
    }

    public Boolean checkShipperIdAndOrderId(String shipperId, String orderId){
        return trackingRepository.findByShipperIdAndOrderId(shipperId,orderId);
    }



}
