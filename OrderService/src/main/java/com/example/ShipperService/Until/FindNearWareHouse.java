package com.example.ShipperService.Until;

import com.example.ShipperService.Model.GeoPoint;
import com.example.ShipperService.Model.WareHouse;
import com.example.ShipperService.Repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component  // Đăng ký với Spring
public class FindNearWareHouse {

    private static final double EARTH_RADIUS = 6371;

    @Autowired
    private WareHouseRepository wareHouseRepository;  // Inject repository


    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c; // Khoảng cách tính bằng km
    }

    public WareHouse findNearestWarehouse(double userLat, double userLon) {
        List<WareHouse> warehouses = wareHouseRepository.findAll(); // Lấy danh sách kho từ DB
        WareHouse nearestWarehouse = null;
        double minDistance = Double.MAX_VALUE;

        for (WareHouse warehouse : warehouses) {
            GeoPoint location =  warehouse.getLocation();
            double lat = (double) location.getLatitude();
            double lon = (double) location.getLongitude();
            double distance = calculateDistance(userLat, userLon, lat, lon);

            if (distance < minDistance) {
                minDistance = distance;
                nearestWarehouse = new WareHouse(warehouse.getId(), warehouse.getName(), warehouse.getType(),warehouse.getLocation());
            }
        }
        return nearestWarehouse;
    }

    public WareHouse findNearestWarehouseCentral(double userLat, double userLon) {
        List<WareHouse> warehouses = wareHouseRepository.findByType("central"); // Lấy danh sách kho từ DB
        WareHouse nearestWarehouse = null;
        double minDistance = Double.MAX_VALUE;

        for (WareHouse warehouse : warehouses) {
            GeoPoint location =  warehouse.getLocation();
            double lat = (double) location.getLatitude();
            double lon = (double) location.getLongitude();
            double distance = calculateDistance(userLat, userLon, lat, lon);

            if (distance < minDistance) {
                minDistance = distance;
                nearestWarehouse = new WareHouse(warehouse.getId(), warehouse.getName(), warehouse.getType(),warehouse.getLocation());
            }
        }
        return nearestWarehouse;
    }
}
