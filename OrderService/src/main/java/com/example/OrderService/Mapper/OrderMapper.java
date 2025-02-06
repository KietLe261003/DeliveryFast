package com.example.OrderService.Mapper;

import com.example.OrderService.Dto.Request.Order.CreateOrder;
import com.example.OrderService.Dto.Request.Order.UpdateOrder;
import com.example.OrderService.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(CreateOrder createOrder);
    void updateOrder(@MappingTarget Order order, UpdateOrder updateOrder);
}
