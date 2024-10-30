package com.example.DeviceManagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceDetailsDto {

    private String description;
    private String address;
    private int maxHourlyConsumption;
}
