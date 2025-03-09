package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.Order.CreateOrder;
import com.example.ShipperService.Dto.Request.Order.UpdateOrder;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Dto.Response.InfoUser;
import com.example.ShipperService.Dto.Response.OrderResponse;
import com.example.ShipperService.Event.OrderPlacedEvent;
import com.example.ShipperService.Mapper.OrderMapper;
import com.example.ShipperService.Model.GeoPoint;
import com.example.ShipperService.Model.Order;
import com.example.ShipperService.Model.Tracking;
import com.example.ShipperService.Model.WareHouse;
import com.example.ShipperService.Repository.HttpClient.UserClient;
import com.example.ShipperService.Repository.OrderRepository;
import com.example.ShipperService.Repository.TrackingRepository;
import com.example.ShipperService.Repository.WareHouseRepository;
import com.example.ShipperService.Until.FindNearWareHouse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserClient userClient;
    @Autowired
    TrackingRepository trackingRepository;
    @Autowired
    FindNearWareHouse findNearWareHouse;
    //String uploadPath = "D:/ProjectCode/SpringBoot/DeliveryFast/orders/";

    String uploadPath = "/app/data/orders/";

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

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
        checkUrlFile();
        Order order = orderMapper.toOrder(createOrder);
        order.setOrderCode(randomCode());
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        // Tìm kho ở gần chỗ lấy hàng
        WareHouse nearestSenderWarehouse = findNearWareHouse.findNearestWarehouse(
                order.getLocationSender().getLatitude(),
                order.getLocationSender().getLongitude()
        );
        // Tìm kho ở gần nơi nhận hàng
        WareHouse nearestReceiverWarehouse = findNearWareHouse.findNearestWarehouse(
                order.getLocationReciver().getLatitude(),
                order.getLocationReciver().getLongitude()
        );
        // Tìm kho trung chuyển gần kho gửi hàng
        WareHouse nearSenderWarehouseCentral = findNearWareHouse.findNearestWarehouseCentral(
                nearestSenderWarehouse.getLocation().getLatitude(),
                nearestSenderWarehouse.getLocation().getLongitude()
        );
        // Tìm kho trung chuyển gần kho nhận hàng
        WareHouse nearReceiverWarehouseCentral = findNearWareHouse.findNearestWarehouseCentral(
                nearestReceiverWarehouse.getLocation().getLatitude(),
                nearestReceiverWarehouse.getLocation().getLongitude()
        );

        ApiResponse checkUser = userClient.findUserById(createOrder.getUserId());
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : createOrder.getImages()) {
            String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            File imageFile = new File(uploadPath + imageName);
            image.transferTo(imageFile); // Lưu file
            imageUrls.add("/orders/" + imageName); // Lưu URL file
        }
        order.setImageUrls(imageUrls);

        if (checkUser.getCode() == 200) {
            orderRepository.save(order);
            List<Tracking> listTracking = new ArrayList<>();

            // 1️⃣ Shipper lấy hàng từ khách → Kho cơ sở gần nhất
            listTracking.add(new Tracking(null,order.getId(), "Chờ lấy hàng","pending",
                    new GeoPoint(order.getLocationSender().getLatitude(), order.getLocationSender().getLongitude()), LocalDateTime.now(),""));

            listTracking.add(new Tracking(null,order.getId(), "Đã đến kho cơ sở "+nearestSenderWarehouse.getName(),"pending",
                    new GeoPoint(nearestSenderWarehouse.getLocation().getLatitude(), nearestSenderWarehouse.getLocation().getLongitude()), LocalDateTime.now(),""));

            // 2️⃣ Nếu kho cơ sở khác kho trung chuyển, đưa hàng đến kho trung chuyển
            if (!nearestSenderWarehouse.equals(nearSenderWarehouseCentral)) {
                listTracking.add(new Tracking(null,order.getId(), "Đang đến kho trung chuyển "+nearSenderWarehouseCentral.getName(),"pending",
                        new GeoPoint(nearSenderWarehouseCentral.getLocation().getLatitude(), nearSenderWarehouseCentral.getLocation().getLongitude()), LocalDateTime.now(),""));
            }

            // 3️⃣ Vận chuyển từ kho trung chuyển đến kho cơ sở gần điểm nhận
            if (!nearSenderWarehouseCentral.equals(nearReceiverWarehouseCentral)) {
                listTracking.add(new Tracking(null,order.getId(), "Đang vận chuyển đến kho trung chuyển gần điểm nhận "+nearReceiverWarehouseCentral.getName(),"pending",
                        new GeoPoint(nearReceiverWarehouseCentral.getLocation().getLatitude(), nearReceiverWarehouseCentral.getLocation().getLongitude()), LocalDateTime.now(),""));
            }

            listTracking.add(new Tracking(null,order.getId(), "Đã đến kho cơ sở gần người nhận "+nearestReceiverWarehouse.getName(),"pending",
                    new GeoPoint(nearestReceiverWarehouse.getLocation().getLatitude(), nearestReceiverWarehouse.getLocation().getLongitude()), LocalDateTime.now(),""));

            // 4️⃣ Shipper giao hàng từ kho cơ sở đến người nhận
            listTracking.add(new Tracking(null,order.getId(), "Đang giao hàng đến người nhận","pending",
                    new GeoPoint(order.getLocationReciver().getLatitude(), order.getLocationReciver().getLongitude()), LocalDateTime.now(),""));

            // Lưu tất cả Tracking vào database
            trackingRepository.saveAll(listTracking);

            // Gửi sự kiện Kafka thông báo đơn hàng mới
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getId()));

            return order;
        } else {
            throw new AppException(ErrorCode.NotFoundUser);
        }
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
                imageUrls.add("/orders/" + imageName); // Lưu URL file
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
    public boolean checkUrlFile()
    {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new RuntimeException("Không thể tạo thư mục lưu trữ: " + uploadPath);
            }
            return true;
        }
        return true;
    }
    public String randomCode()
    {
        Random random = new Random();
        int codeRandom = 100000 + random.nextInt(900000);
        return String.valueOf(codeRandom);
    }

}
