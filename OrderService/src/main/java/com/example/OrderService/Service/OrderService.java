package com.example.OrderService.Service;

import com.example.OrderService.Base_Exception.AppException;
import com.example.OrderService.Base_Exception.ErrorCode;
import com.example.OrderService.Dto.Request.Order.CreateOrder;
import com.example.OrderService.Dto.Request.Order.UpdateOrder;
import com.example.OrderService.Dto.Response.ApiResponse;
import com.example.OrderService.Mapper.OrderMapper;
import com.example.OrderService.Model.Order;
import com.example.OrderService.Repository.HttpClient.UserClient;
import com.example.OrderService.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserClient userClient;
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        return orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrder));
    }

    public Order create(CreateOrder createOrder) {
        Order order = orderMapper.toOrder(createOrder);
        order.setOrderCode(randomCode());
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        ApiResponse checkUser = userClient.findUserById(createOrder.getUserId());
        if(checkUser.getCode()==200)
            return orderRepository.save(order);
        else
            throw new AppException(ErrorCode.NotFoundUser);
    }
    public Order update(String id, UpdateOrder updateOrder) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrder));
        orderMapper.updateOrder(order, updateOrder);
        order.setUpdateAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
    public Order delete(String id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrder));
        orderRepository.delete(order);
        return order;
    }
    public String randomCode()
    {
        Random random = new Random();
        int codeRandom = 100000 + random.nextInt(900000);
        return String.valueOf(codeRandom);
    }
}
