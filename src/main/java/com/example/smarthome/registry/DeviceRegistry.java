package com.example.smarthome.registry;

import com.example.smarthome.device.SmartDevice;

import java.util.List;

public class DeviceRegistry {

    private final List<SmartDevice> devices;

    public DeviceRegistry(List<SmartDevice> devices) {
        this.devices = devices;
    }

    public List<SmartDevice> getAllDevices() {
        return devices;
    }

    public SmartDevice getById(String id) {
        return devices.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void registerDevice(SmartDevice device) {
        devices.add(device);
    }

    public void unregisterDevice(SmartDevice device) {
        devices.remove(device);
    }

}