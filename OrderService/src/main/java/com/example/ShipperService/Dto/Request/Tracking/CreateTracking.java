package com.example.ShipperService.Dto.Request.Tracking;

import com.example.ShipperService.Model.GeoPoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTracking {
    String orderId;
    GeoPoint location;
}
