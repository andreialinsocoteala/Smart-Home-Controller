package com.example.smarthome.service;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.logging.StateChange;
import com.example.smarthome.logging.StateChangeLogger;
import com.example.smarthome.registry.DeviceRegistry;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmartHomeService {

    private final DeviceRegistry registry;
    private final StateChangeLogger logger;

    public SmartHomeService(DeviceRegistry registry, StateChangeLogger logger) {
        this.registry = registry;
        this.logger = logger;
    }

    public Map<String, Object> getDeviceDetails(String id) {
        SmartDevice d = requireDevice(id);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", d.getId());
        map.put("name", d.getName());
        map.put("status", d.getStatus());
        Integer level = d.getLevel();
        if (level != null) {
            map.put("level", level);
        }
        return map;
    }

    public List<LinkedHashMap<String, Object>> getAllDevices() {
        return registry.getAllDevices()
                .stream()
                .map(d -> {
                    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                    map.put("id", d.getId());
                    map.put("name", d.getName());
                    map.put("status", d.getStatus());
                    Integer level = d.getLevel();
                    if (level != null) {
                        map.put("level", level);
                    }
                    return map;
                })
                .toList();
    }

    public String turnOn(String id) {
        SmartDevice device = requireDevice(id);
        device.turnOn();
        return "Device " + id + " turned ON";
    }

    public String turnOff(String id) {
        SmartDevice device = requireDevice(id);
        device.turnOff();
        return "Device " + id + " turned OFF";
    }

    public String setLevel(String id, int level) {
        SmartDevice device = requireDevice(id);
        device.setLevel(level);
        return "Device " + id + " level set to " + level;
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
