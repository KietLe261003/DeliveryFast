package com.example.OrderService.Mapper;

import com.example.OrderService.Dto.Request.Tracking.CreateTracking;
import com.example.OrderService.Model.Tracking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackingMapper {
    Tracking toTracking(CreateTracking createTracking);
}
