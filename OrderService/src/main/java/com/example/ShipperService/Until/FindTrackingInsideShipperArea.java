package com.example.ShipperService.Until;

import com.example.ShipperService.Model.GeoPoint;
import com.example.ShipperService.Model.Tracking;
import com.example.ShipperService.Repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class FindTrackingInsideShipperArea {
    public boolean isPointInArea(GeoPoint point, List<GeoPoint> polygon) {
        int n = polygon.size();
        if (n < 2) return false;

        if (n == 2) {
            double x = point.getLatitude();
            double y = point.getLongitude();
            double x1 = polygon.get(0).getLatitude();
            double y1 = polygon.get(0).getLongitude();
            double x2 = polygon.get(1).getLatitude();
            double y2 = polygon.get(1).getLongitude();

            double distP1P2 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            double distP1Point = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
            double distP2Point = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
            return Math.abs(distP1P2 - (distP1Point + distP2Point)) < 1e-10;
        }

        boolean inside = false;
        double px = point.getLatitude();
        double py = point.getLongitude();
        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon.get(i).getLatitude();
            double yi = polygon.get(i).getLongitude();
            double xj = polygon.get(j).getLatitude();
            double yj = polygon.get(j).getLongitude();
            if ((xi == px && yi == py) || (xj == px && yj == py)) {
                return true;
            }
            boolean intersect = ((yi > py) != (yj > py)) &&
                    (px < (xj - xi) * (py - yi) / (yj - yi + 1e-10) + xi);
            if (intersect) {
                inside = !inside;
            }
        }
        return inside;
    }

    public List<Tracking> findTrackingsInShipperArea(List<GeoPoint> shipperArea, List<Tracking> trackings, String currentShipperId) {
        List<Tracking> result = new ArrayList<>();

        List<Tracking> sortedTrackings = new ArrayList<>(trackings);
        sortedTrackings.sort(Comparator.comparing(Tracking::getUpdateTimeStamp));

        for (Tracking tracking : sortedTrackings) {
            boolean isInArea = isPointInArea(tracking.getLocation(), shipperArea);
            boolean isInNextArea = isPointInArea(tracking.getNextLocation(), shipperArea);
//            if (!isInArea) {
//                continue;
//            }
//            // Kiểm tra trạng thái và shipperId
//            boolean isActiveForShipper =
//                    (tracking.getStatus().equals("ready") || tracking.getStatus().equals("shipping")) &&
//                            (tracking.getShipperId() == null || tracking.getShipperId().equals(currentShipperId));
//
//            // Kiểm tra xem có tracking trước đó trong khu vực đã hoàn thành không
//            boolean isPreviousCompleteInArea = sortedTrackings.stream()
//                    .filter(t -> t.getOrderId().equals(tracking.getOrderId()))
//                    .filter(t -> t.getUpdateTimeStamp().isBefore(tracking.getUpdateTimeStamp()))
//                    .filter(t -> isPointInArea(t.getLocation(), shipperArea))
//                    .anyMatch(t -> t.getStatus().equals("complete"));
//
//            // Chỉ thêm tracking nếu nó chưa hoàn thành và không có tracking hoàn thành trước đó trong khu vực
//            if (isInArea && isActiveForShipper && !isPreviousCompleteInArea) {
//                result.add(tracking);
//            }
            if (isInArea && isInNextArea) {
                result.add(tracking);
            }
        }

        return result;
    }
}
