package com.example.ShipperService.Dto.Request.WareHouse;

import com.example.ShipperService.Model.GeoPoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WareHouseRequest {
    String name;
    String type;
    GeoPoint location;
}
