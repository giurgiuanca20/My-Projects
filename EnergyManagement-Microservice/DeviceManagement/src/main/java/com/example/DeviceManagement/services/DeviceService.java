package com.example.DeviceManagement.services;

import com.example.DeviceManagement.controllers.handlers.DeviceNotFoundException;
import com.example.DeviceManagement.dtos.DeviceDetailsDto;
import com.example.DeviceManagement.dtos.MappingDto;
import com.example.DeviceManagement.dtos.builders.DeviceBuilder;
import com.example.DeviceManagement.entities.Device;
import com.example.DeviceManagement.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    public final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository=deviceRepository;
    }

    public boolean existsByDescription(String description) {
        return deviceRepository.existsByDescription(description);
    }
    public boolean existsByIdAndUserId(Long id,Long userId) {
        return deviceRepository.existsByIdAndUserId(id,userId);
    }


    public void createMapping(MappingDto mappingDto){
        Optional<Device> device=deviceRepository.findByDescription(mappingDto.getDescription());
        if(!device.isPresent()){
            throw new DeviceNotFoundException("Device not found!");
        }
        deviceRepository.updateUserIdByDescription(mappingDto.getDescription(),mappingDto.getUserId());
    }

    @Transactional
    public void deleteMapping(MappingDto mappingDto){
        Optional<Device> device=deviceRepository.findByDescription(mappingDto.getDescription());
        if(!device.isPresent()){
            throw new DeviceNotFoundException("Device not found!");
        }
        deviceRepository.deleteUserIdByUserIdAndDescription(mappingDto.getUserId(),mappingDto.getDescription());
    }
    public List<Device> findAllByUserId(Long userId) {

        List<Device> devices = deviceRepository.findByUserId(userId);
        return devices;

    }


    public Device create(DeviceDetailsDto deviceDetailsDto) {
        Device device = DeviceBuilder.toDevice(deviceDetailsDto);
        return deviceRepository.save(device);
    }

    public Device update(DeviceDetailsDto deviceDetailsDto) {

        Device auxDevice=deviceRepository.findByDescription(deviceDetailsDto.getDescription()).get();
        if(!deviceRepository.findByDescription(deviceDetailsDto.getDescription()).isPresent()){
            throw new DeviceNotFoundException("Device not found!");
        }
        Device device = DeviceBuilder.toDevice(deviceDetailsDto);
        device.setId(auxDevice.getId());
        return deviceRepository.save(device);
    }

    @Transactional
    public void delete(DeviceDetailsDto deviceDetailsDto) {
        Device device=DeviceBuilder.toDevice(deviceDetailsDto);
        deviceRepository.findByDescriptionAndAddressAndMaxHourlyConsumption(device.getDescription(),device.getAddress(),device.getMaxHourlyConsumption()).orElseThrow(() -> {
            return new DeviceNotFoundException("Can't delete. Device not found!");
        });
        deviceRepository.deleteByDescriptionAndAddressAndMaxHourlyConsumption(device.getDescription(),device.getAddress(),device.getMaxHourlyConsumption());
    }

    public Device findById(Long id) {

        Device device = deviceRepository.findById(id).orElseThrow(() -> {
            return new DeviceNotFoundException("Device not found!");
        });
        return device;

    }

    public Long findId(DeviceDetailsDto deviceDetailsDto) {
        return deviceRepository.findId(deviceDetailsDto.getDescription(),deviceDetailsDto.getAddress(),deviceDetailsDto.getMaxHourlyConsumption());
    }

    public List<Device> findAll() {

        List<Device> devices = deviceRepository.findAll();
        return devices;

    }
}
