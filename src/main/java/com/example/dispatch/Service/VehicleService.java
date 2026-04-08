package com.example.dispatch.Service;

import com.example.dispatch.Entity.VehicleEntity;
import com.example.dispatch.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final Random random = new Random();

    public VehicleEntity findNearestVehicle(double userLat, double userLng) {

        List<VehicleEntity> vehicles = vehicleRepository.findAll();

        VehicleEntity nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (VehicleEntity v : vehicles) {

            if (random.nextInt(100) > 70) continue;

            double distance = calculateDistance(
                    userLat, userLng,
                    v.getLatitude(), v.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                nearest = v;
            }
        }

        if (nearest != null) {
            nearest.setStatus("BUSY");
            vehicleRepository.save(nearest);
        }

        return nearest;
    }

    @Scheduled(fixedRate = 60000)
    public void resetStatus() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();

        for (VehicleEntity v : vehicles) {
            if ("BUSY".equals(v.getStatus())) {
                v.setStatus("AVAILABLE");
            }
        }

        vehicleRepository.saveAll(vehicles);
    }

    @Scheduled(fixedRate = 5000)
    public void moveVehicles() {

        List<VehicleEntity> vehicles = vehicleRepository.findAll();

        for (VehicleEntity v : vehicles) {

            double latMove = (Math.random() - 0.5) * 0.01;
            double lngMove = (Math.random() - 0.5) * 0.01;

            v.setLatitude(v.getLatitude() + latMove);
            v.setLongitude(v.getLongitude() + lngMove);
        }

        vehicleRepository.saveAll(vehicles);
    }

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {

        final int R = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}