package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.Shipper.CreateShipper;
import com.example.ShipperService.Dto.Request.UserRequest;
import com.example.ShipperService.Dto.Response.ApiResponse;
import com.example.ShipperService.Dto.Response.ApiUserResponse;
import com.example.ShipperService.Mapper.ShipperMapper;
import com.example.ShipperService.Model.Shipper;
import com.example.ShipperService.Repository.HttpClient.UserClient;
import com.example.ShipperService.Repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private ShipperMapper shipperMapper;
    @Autowired
    private UserClient userClient;

    public List<Shipper> findAll() {
        return shipperRepository.findAll();
    }
    public Shipper findById(String id) {
        return shipperRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NotFoundShipper));
    }
    public Shipper findByUserId(String userId) {
        return shipperRepository.findByUserId(userId).orElseThrow(()->new AppException(ErrorCode.NotFoundShipper));
    }
    public Shipper create(CreateShipper createShipper) {
        Shipper shipper = shipperMapper.toShipper(createShipper);
        UserRequest userRequest = shipperMapper.toUserRequest(createShipper);
        userRequest.setRole("shipper");
        ApiUserResponse apiResponse = userClient.createUser(userRequest);

        if(apiResponse.getCode()!=200)
            throw new AppException(ErrorCode.CreateShipperFail);
        shipper.setUserId(apiResponse.getData().getUserId());
        shipper.setStatus(true);
        shipper.setCreatedAt(LocalDateTime.now());
        shipper.setUpdatedAt(LocalDateTime.now());
        return shipperRepository.save(shipper);
    }
    public Shipper update(String id,CreateShipper createShipper) {
        Shipper shipper = shipperRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NotFoundShipper));
        shipperMapper.updateShipper(shipper,createShipper);
        shipper.setUpdatedAt(LocalDateTime.now());
        return shipperRepository.save(shipper);
    }
    public Shipper delete(String id) {
        Shipper shipper = shipperRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NotFoundShipper));
        shipperRepository.deleteById(id);
        return shipper;
    }
}
