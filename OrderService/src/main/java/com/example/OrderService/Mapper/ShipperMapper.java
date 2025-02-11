package com.example.OrderService.Mapper;

import com.example.OrderService.Dto.Request.Shipper.CreateShipper;
import com.example.OrderService.Model.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    Shipper toShipper(CreateShipper createShipper);
    void updateShipper(@MappingTarget Shipper shipper, CreateShipper createShipper);
}
