package com.example.MonitoringAndCommunicationMicroservice.controllers;


import com.example.MonitoringAndCommunicationMicroservice.services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/monitoring")
public class MonitoringController {
    private final MonitoringService monitoringService;
    @Autowired
    public MonitoringController(MonitoringService monitoringService){
        this.monitoringService=monitoringService;
    }

    @GetMapping("/chart")
    public ResponseEntity getDailyConsumption(
            @RequestParam String deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59).plusSeconds(59);
        return ResponseEntity.ok(monitoringService.getEnergyConsumptionForDay(deviceId, startOfDay, endOfDay));
    }

    @PostMapping("/maxConsumption")
    public void postMaxConsumption(@RequestParam int maxConsumption){
        monitoringService.limit=maxConsumption;
    }

}
