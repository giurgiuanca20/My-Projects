package com.example.DeviceManagement.dtos.builders;

import com.example.DeviceManagement.dtos.DeviceDetailsDto;
import com.example.DeviceManagement.entities.Device;

public class DeviceBuilder {
    public static Device toDevice(DeviceDetailsDto deviceDetailsDto){
        return new Device(deviceDetailsDto.getDescription(),deviceDetailsDto.getAddress(),deviceDetailsDto.getMaxHourlyConsumption());
    }
}
