package com.example.ShipperService.Service;

import com.example.ShipperService.Base_Exception.AppException;
import com.example.ShipperService.Base_Exception.ErrorCode;
import com.example.ShipperService.Dto.Request.WareHouse.WareHouseRequest;
import com.example.ShipperService.Mapper.WareHouseMapper;
import com.example.ShipperService.Model.WareHouse;
import com.example.ShipperService.Repository.WareHouseRepository;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WareHouseService {
    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private WareHouseMapper wareHouseMapper;

    public List<WareHouse> getAllWareHouse(){
        return wareHouseRepository.findAll();
    }
    public WareHouse getWareHouseById(String id){
        return wareHouseRepository.findById(id).get();
    }
    public WareHouse addWareHouse(WareHouseRequest wareHouseRequest){
        WareHouse wareHouse = wareHouseMapper.toWareHouse(wareHouseRequest);
        return wareHouseRepository.save(wareHouse);
    }
    public List<WareHouse> addListWareHouse(List<WareHouseRequest> listWareHouseRequest){
        List<WareHouse> wareHouseList = new ArrayList<>();
        for (WareHouseRequest wareHouseRequest : listWareHouseRequest) {
            wareHouseList.add(wareHouseMapper.toWareHouse(wareHouseRequest));
        }
        return wareHouseRepository.saveAll(wareHouseList);
    }
    public WareHouse updateWareHouse(String id,WareHouseRequest wareHouseRequest){
        WareHouse wareHouse = wareHouseRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NotFoundWarehouse));
        wareHouseMapper.updateWareHouse(wareHouse,wareHouseRequest);
        return wareHouseRepository.save(wareHouse);
    }
    public String deleteWareHouse(String id){
        wareHouseRepository.deleteById(id);
        return "Deleted successfully";
    }
}
