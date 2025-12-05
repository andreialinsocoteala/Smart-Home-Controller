package com.example.smarthome.service;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.logging.StateChange;
import com.example.smarthome.logging.StateChangeLogger;
import com.example.smarthome.registry.DeviceRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartHomeService {

    private final DeviceRegistry registry;
    private final StateChangeLogger logger;

    public SmartHomeService(DeviceRegistry registry, StateChangeLogger logger) {
        this.registry = registry;
        this.logger = logger;
    }

    public SmartDevice getDevice(String id) {
        return requireDevice(id);
    }

    public List<SmartDevice> getAllDevices() {
        return registry.getAllDevices();
    }

    public SmartDevice turnOn(String id) {
        SmartDevice device = requireDevice(id);
        device.turnOn();
        return device;
    }

    public SmartDevice turnOff(String id) {
        SmartDevice device = requireDevice(id);
        device.turnOff();
        return device;
    }

    public SmartDevice setLevel(String id, int level) {
        SmartDevice device = requireDevice(id);
        if (device.getLevel() == null) {
            throw new IllegalArgumentException(
                    "Device " + id + " does not support level control."
            );
        }
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Level must be between 0 and 100.");
        }
        device.setLevel(level);
        return device;
    }

    public List<StateChange> getLog() {
        return logger.getChanges();
    }

    private SmartDevice requireDevice(String id) {
        SmartDevice d = registry.getById(id);
        if (d == null) {
            throw new IllegalArgumentException("Device not found: " + id);
        }
        return d;
    }
}
