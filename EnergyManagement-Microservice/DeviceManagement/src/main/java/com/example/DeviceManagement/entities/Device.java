package com.example.DeviceManagement.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GeneratedValue;


@Entity
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "device_id")
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int maxHourlyConsumption;
    @Column
    private Long userId;

    public Device(String description, String address, int maxHourlyConsumption) {
        this.description = description;
        this.address = address;
        this.maxHourlyConsumption = maxHourlyConsumption;
    }

    public Device() {
    }

}
