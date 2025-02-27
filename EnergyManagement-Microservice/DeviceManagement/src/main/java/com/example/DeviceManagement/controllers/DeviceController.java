package com.example.DeviceManagement.controllers;

import com.example.DeviceManagement.dtos.DeviceDetailsDto;
import com.example.DeviceManagement.dtos.MappingDto;
import com.example.DeviceManagement.entities.Device;
import com.example.DeviceManagement.producer.SmartMeterSimulator;
import com.example.DeviceManagement.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.Role;
import java.io.*;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService deviceService;
    private final SmartMeterSimulator smartMeterSimulator;

    @Autowired
    public DeviceController(DeviceService deviceService,SmartMeterSimulator smartMeterSimulator){
        this.deviceService=deviceService;
        this.smartMeterSimulator=smartMeterSimulator;
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

    @GetMapping("/findByDeviceAndUser")
    public boolean existsByIdAndUserId(@RequestParam Long id, @RequestParam Long userId) {
        return deviceService.existsByIdAndUserId(id,userId);
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

    @PostMapping("/findId")
    public ResponseEntity<Long> findId(@RequestParam String simulareId,
            @RequestBody DeviceDetailsDto deviceDetailsDto) {
        Long id = deviceService.findId(deviceDetailsDto);
        System.out.println(simulareId);
        try {
           // String filePath = "/app/config"+simulareId+".properties";
            String filePath ="config1.properties";
            Properties properties = new Properties();

            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                properties.load(inputStream);
            } catch (FileNotFoundException e) {
            }

            properties.setProperty("device.id", id.toString());


            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                properties.store(outputStream, null);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(id, HttpStatus.FOUND);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Device>> findAll(){
        List<Device> devices=deviceService.findAll();
        return new ResponseEntity<>(devices,HttpStatus.FOUND);
    }
    @GetMapping("/start")
    public void start(@RequestParam String simulareId){
        smartMeterSimulator.loadConfig(simulareId);
       smartMeterSimulator.start();
    }

}
