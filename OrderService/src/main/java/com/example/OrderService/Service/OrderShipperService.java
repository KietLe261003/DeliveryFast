package com.example.OrderService.Service;

import com.example.OrderService.Base_Exception.AppException;
import com.example.OrderService.Base_Exception.ErrorCode;
import com.example.OrderService.Dto.Request.OrderShipper.CreateOrderShipper;
import com.example.OrderService.Dto.Request.OrderShipper.UpdateOrderShipper;
import com.example.OrderService.Mapper.OrderShipperMapper;
import com.example.OrderService.Model.Order;
import com.example.OrderService.Model.OrderShipper;
import com.example.OrderService.Repository.OrderRepository;
import com.example.OrderService.Repository.OrderShipperRepository;
import com.example.OrderService.Repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderShipperService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderShipperRepository orderShipperRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private OrderShipperMapper orderShipperMapper;
    public List<OrderShipper> findAll(){
        return orderShipperRepository.findAll();
    }
    public OrderShipper findById(String id){
        return orderShipperRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrderShipper));
    }
    public OrderShipper create(CreateOrderShipper createOrderShipper){
        OrderShipper newOrderShipper = orderShipperMapper.toOrderShipper(createOrderShipper);
        Order order = orderRepository.findById(createOrderShipper.getOrderId()).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrder));
        if(!shipperRepository.findById(createOrderShipper.getShipperId()).isPresent()){
            throw new AppException(ErrorCode.NotFoundShipper);
        }
        newOrderShipper.setLocationDelivery(order.getReceiverAddress());
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
