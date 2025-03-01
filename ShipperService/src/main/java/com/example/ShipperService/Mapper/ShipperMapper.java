package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.Shipper.CreateShipper;
import com.example.ShipperService.Model.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    Shipper toShipper(CreateShipper createShipper);
    void updateShipper(@MappingTarget Shipper shipper, CreateShipper createShipper);
}
