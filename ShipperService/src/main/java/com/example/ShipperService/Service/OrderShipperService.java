package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.OrderShipper.CreateOrderShipper;
import com.example.ShipperService.Dto.Request.OrderShipper.UpdateOrderShipper;
import com.example.ShipperService.Dto.Response.ApiResponseOrder;
import com.example.ShipperService.Mapper.OrderShipperMapper;
import com.example.ShipperService.Model.OrderShipper;
import com.example.ShipperService.Repository.HttpClient.OrderClient;
import com.example.ShipperService.Repository.OrderShipperRepository;
import com.example.ShipperService.Repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderShipperService {
    @Autowired
    private OrderShipperRepository orderShipperRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private OrderShipperMapper orderShipperMapper;
    @Autowired
    private OrderClient orderClient;
    public List<OrderShipper> findAll(){
        return orderShipperRepository.findAll();
    }
    public OrderShipper findById(String id){
        return orderShipperRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrderShipper));
    }
    public OrderShipper create(CreateOrderShipper createOrderShipper){
        OrderShipper newOrderShipper = orderShipperMapper.toOrderShipper(createOrderShipper);
        ApiResponseOrder apiResponseOrder = orderClient.findOrderById(createOrderShipper.getOrderId());
        if(!shipperRepository.findById(createOrderShipper.getShipperId()).isPresent()){
            throw new AppException(ErrorCode.NotFoundShipper);
        }
        newOrderShipper.setLocationDelivery(apiResponseOrder.getData().getReceiverAddress());
        newOrderShipper.setAssignedAt(LocalDateTime.now());
        newOrderShipper.setStatus("Pending");
        return orderShipperRepository.save(newOrderShipper);
    }
    public OrderShipper update(String id,UpdateOrderShipper updateOrderShipper){
        OrderShipper orderShipper = orderShipperRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrderShipper));
        orderShipperMapper.updateOrderShipper(orderShipper,updateOrderShipper);
        return orderShipperRepository.save(orderShipper);
    }
    public OrderShipper delete(String id){
        OrderShipper orderShipper = orderShipperRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrderShipper));
        orderShipperRepository.delete(orderShipper);
        return orderShipper;
    }
    public List<OrderShipper> findByShipperId(String shipperId) {
        return orderShipperRepository.findByShipperId(shipperId);
    }
    public List<OrderShipper> findByOrderId(String orderId) {
        return orderShipperRepository.findByOrderId(orderId);
    }
}
