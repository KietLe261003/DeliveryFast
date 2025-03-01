package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.Order.CreateOrder;
import com.example.ShipperService.Dto.Request.Order.UpdateOrder;
import com.example.ShipperService.Dto.Response.OrderResponse;
import com.example.ShipperService.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(CreateOrder createOrder);
    OrderResponse toOrderResponse(Order order);
    void updateOrder(@MappingTarget Order order, UpdateOrder updateOrder);
}
