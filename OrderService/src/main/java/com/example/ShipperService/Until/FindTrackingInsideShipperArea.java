package com.example.ShipperService.Until;

import com.example.ShipperService.Model.GeoPoint;
import com.example.ShipperService.Model.Tracking;
import com.example.ShipperService.Repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindTrackingInsideShipperArea {
    public boolean isPointInPolygon(GeoPoint point, List<GeoPoint> polygon) {
        int n = polygon.size();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon.get(i).getLatitude();
            double yi = polygon.get(i).getLongitude();
            double xj = polygon.get(j).getLatitude();
            double yj = polygon.get(j).getLongitude();

            boolean intersect = ((yi > point.getLongitude()) != (yj > point.getLongitude())) &&
                    (point.getLatitude() < (xj - xi) * (point.getLongitude() - yi) / (yj - yi) + xi);
            if (intersect) {
                inside = !inside;
            }
        }
        return inside;
    }
    public List<Tracking> findTrackingsInShipperArea(List<GeoPoint> shipperArea, List<Tracking> trackings) {
        List<Tracking> result = new ArrayList<>();

        for (Tracking tracking : trackings) {
            if (isPointInPolygon(tracking.getLocation(), shipperArea)) {
                result.add(tracking);
            }
        }

        return result;
    }
}
