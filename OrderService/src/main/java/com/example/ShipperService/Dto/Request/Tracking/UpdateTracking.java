package com.example.ShipperService.Dto.Request.Tracking;

import com.example.ShipperService.Model.GeoPoint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateTracking {
    String status;
    String ShipperId;
}
