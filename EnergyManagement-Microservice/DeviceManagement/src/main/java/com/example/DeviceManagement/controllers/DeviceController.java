package com.example.DeviceManagement.controllers;

import com.example.DeviceManagement.dtos.DeviceDetailsDto;
import com.example.DeviceManagement.dtos.MappingDto;
import com.example.DeviceManagement.entities.Device;
import com.example.DeviceManagement.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService=deviceService;
    }

    @PostMapping("/create/mapping")
    public ResponseEntity<String> createMapping(@RequestBody MappingDto mappingDto){
        deviceService.createMapping(mappingDto);
        return new ResponseEntity<>("Device mapped to user!",HttpStatus.CREATED);
    }
    @PatchMapping("/delete/mapping")
    public ResponseEntity<String> deleteMapping(@RequestBody MappingDto mappingDto){
        deviceService.deleteMapping(mappingDto);
        return new ResponseEntity<>("Mapping deleted!",HttpStatus.OK);
    }

    @GetMapping("/findAll/mapping")
    public ResponseEntity<List<Device>> findAll(@RequestParam Long userId){
        List<Device> devices=deviceService.findAllByUserId(userId);
        return new ResponseEntity<>(devices,HttpStatus.FOUND);
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody DeviceDetailsDto deviceDetailsDto){

        if (deviceService.existsByDescription(deviceDetailsDto.getDescription())) {
            return new ResponseEntity<>("Error: Description is already taken!",HttpStatus.BAD_REQUEST);
        }
        deviceService.create(deviceDetailsDto);
        return new ResponseEntity<>("Device created!",HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody DeviceDetailsDto deviceDetailsDto){
        deviceService.update(deviceDetailsDto);
        return new ResponseEntity<>("Device updated!",HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody DeviceDetailsDto deviceDetailsDto){
        deviceService.delete(deviceDetailsDto);
        return new ResponseEntity<>("Device deleted!",HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<Device> findById(@RequestBody Long id){
        Device device=deviceService.findById(id);
        return new ResponseEntity<>(device,HttpStatus.FOUND);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Device>> findAll(){
        List<Device> devices=deviceService.findAll();
        return new ResponseEntity<>(devices,HttpStatus.FOUND);
    }

}
