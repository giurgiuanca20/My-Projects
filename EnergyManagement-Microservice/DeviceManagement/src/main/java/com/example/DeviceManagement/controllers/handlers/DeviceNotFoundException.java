package com.example.DeviceManagement.controllers.handlers;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(String message) {
        super(message);
    }

}
