package com.example.MonitoringAndCommunicationMicroservice.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EnergyConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String deviceId;
    @Column(nullable = false)
    private double hourlyConsumption;
    @Column(nullable = false)
    private long timestamp;

    public EnergyConsumption(String deviceId, double hourlyConsumption, long timestamp) {
        this.deviceId = deviceId;
        this.hourlyConsumption = hourlyConsumption;
        this.timestamp = timestamp;
    }

    public EnergyConsumption() {
    }
}
