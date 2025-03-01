package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.Tracking.CreateTracking;
import com.example.ShipperService.Model.Tracking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackingMapper {
    Tracking toTracking(CreateTracking createTracking);
}
