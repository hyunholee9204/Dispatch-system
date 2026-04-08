package com.example.dispatch.Controller;

import com.example.dispatch.Entity.VehicleEntity;
import com.example.dispatch.Service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/")
    public String mainPage(Model model) {

        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);

        return "MainPage";
    }

    @PostMapping("/dispatch")
    public String dispatch(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lng,
            Model model
    ) {

        if (lat != null && lng != null) {
            VehicleEntity vehicle = vehicleService.findNearestVehicle(lat, lng);
            model.addAttribute("vehicle", vehicle);

            model.addAttribute("lat", lat);
            model.addAttribute("lng", lng);
        }

        List<VehicleEntity> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);

        return "MainPage";
    }
}