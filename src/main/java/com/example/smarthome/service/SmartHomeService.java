package com.example.smarthome.service;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.logging.StateChange;
import com.example.smarthome.logging.StateChangeLogger;
import com.example.smarthome.registry.DeviceRegistry;
import com.example.smarthome.session.ApplicationStats;
import com.example.smarthome.session.UserSessionPreferences;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartHomeService {

    private final DeviceRegistry registry;
    private final StateChangeLogger logger;
    private final UserSessionPreferences sessionPreferences;
    private final ApplicationStats applicationStats;

    public SmartHomeService(DeviceRegistry registry, StateChangeLogger logger,
                            UserSessionPreferences sessionPreferences, ApplicationStats applicationStats) {
        this.registry = registry;
        this.logger = logger;
        this.sessionPreferences = sessionPreferences;
        this.applicationStats = applicationStats;
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
        sessionPreferences.registerAction(id);
        applicationStats.registerOn();
        return device;
    }

    public SmartDevice turnOff(String id) {
        SmartDevice device = requireDevice(id);
        device.turnOff();
        sessionPreferences.registerAction(id);
        applicationStats.registerOff();
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
        sessionPreferences.registerAction(id);
        applicationStats.registerLevelChange();
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
