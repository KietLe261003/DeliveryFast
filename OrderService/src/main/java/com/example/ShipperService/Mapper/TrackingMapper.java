package com.example.ShipperService.Mapper;

import com.example.ShipperService.Dto.Request.Tracking.CreateTracking;
import com.example.ShipperService.Dto.Request.Tracking.UpdateTracking;
import com.example.ShipperService.Model.Tracking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrackingMapper {
    Tracking toTracking(CreateTracking createTracking);
    void updateTracking(@MappingTarget Tracking tracking, UpdateTracking updateTracking);
}
