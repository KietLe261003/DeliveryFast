package com.example.OrderService.Service;

import com.example.OrderService.Base_Exception.AppException;
import com.example.OrderService.Base_Exception.ErrorCode;
import com.example.OrderService.Dto.Request.Order.CreateOrder;
import com.example.OrderService.Dto.Request.Order.UpdateOrder;
import com.example.OrderService.Dto.Response.ApiResponse;
import com.example.OrderService.Dto.Response.InfoUser;
import com.example.OrderService.Dto.Response.OrderResponse;
import com.example.OrderService.Mapper.OrderMapper;
import com.example.OrderService.Model.Order;
import com.example.OrderService.Model.OrderShipper;
import com.example.OrderService.Repository.HttpClient.UserClient;
import com.example.OrderService.Repository.OrderRepository;
import com.example.OrderService.Repository.OrderShipperRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserClient userClient;
    String uploadPath = "D:/ProjectCode/SpringBoot/DeliveryFast/Order/";
    @Autowired
    private OrderShipperRepository orderShipperRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public OrderResponse findById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NotFoundOrder));
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        ApiResponse orderUser = userClient.findUserById(order.getUserId());
        if(orderUser.getCode()==200)
        {
            ObjectMapper mapper = new ObjectMapper();
            orderResponse.setInfoUser(mapper.convertValue(orderUser.getData(), InfoUser.class));
            return orderResponse;
        }
        else
            throw new AppException(ErrorCode.NotFoundUser);
    }

    public Order create(CreateOrder createOrder) throws IOException {
        Order order = orderMapper.toOrder(createOrder);
        order.setOrderCode(randomCode());
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        ApiResponse checkUser = userClient.findUserById(createOrder.getUserId());
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : createOrder.getImages()) {
            String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            File imageFile = new File(uploadPath + imageName);
            image.transferTo(imageFile); // Lưu file
            imageUrls.add("/Order/" + imageName); // Lưu URL file
        }

        order.setImageUrls(imageUrls);
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
    public List<Order> CreateListOrder(List<CreateOrder> createOrder) {
        List<Order> orderList = new ArrayList<>();
        createOrder.forEach(order -> {
            Order newOrder = orderMapper.toOrder(order);
            newOrder.setOrderCode(randomCode());
            newOrder.setCreateAt(LocalDateTime.now());
            newOrder.setUpdateAt(LocalDateTime.now());
            ApiResponse checkUser = userClient.findUserById(order.getUserId());
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile image :order.getImages()) {
                String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                File imageFile = new File(uploadPath + imageName);
                try {
                    image.transferTo(imageFile); // Lưu file
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imageUrls.add("/Order/" + imageName); // Lưu URL file
            }
            newOrder.setImageUrls(imageUrls);
            if(checkUser.getCode()==200)
            {
                orderRepository.save(newOrder);
                orderList.add(newOrder);
            }
        });
        return orderList;
    }
    public String randomCode()
    {
        Random random = new Random();
        int codeRandom = 100000 + random.nextInt(900000);
        return String.valueOf(codeRandom);
    }

}
