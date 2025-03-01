package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.OrderShipper.CreateOrderShipper;
import com.example.ShipperService.Dto.Request.OrderShipper.UpdateOrderShipper;
import com.example.ShipperService.Model.OrderShipper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderShipperMapper {
    OrderShipper toOrderShipper(CreateOrderShipper createOrderShipper);
    void updateOrderShipper(@MappingTarget OrderShipper orderShipper, UpdateOrderShipper updateOrderShipper);
}
