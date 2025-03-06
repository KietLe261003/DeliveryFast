package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.WareHouse.WareHouseRequest;
import com.example.ShipperService.Model.WareHouse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WareHouseMapper {
    WareHouse toWareHouse(WareHouseRequest wareHouseRequest);
    void updateWareHouse(@MappingTarget WareHouse wareHouse, WareHouseRequest wareHouseRequest);
}
