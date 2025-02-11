package com.example.OrderService.Mapper;

import com.example.OrderService.Dto.Request.OrderShipper.CreateOrderShipper;
import com.example.OrderService.Dto.Request.OrderShipper.UpdateOrderShipper;
import com.example.OrderService.Model.OrderShipper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderShipperMapper {
    OrderShipper toOrderShipper(CreateOrderShipper createOrderShipper);
    void updateOrderShipper(@MappingTarget OrderShipper orderShipper, UpdateOrderShipper updateOrderShipper);
}
