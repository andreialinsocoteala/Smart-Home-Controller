package com.example.smarthome.controller;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.logging.StateChange;
import com.example.smarthome.logging.StateChangeLogger;
import com.example.smarthome.registry.DeviceRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
public class SmartHomeController {

    private final DeviceRegistry registry;
    private final StateChangeLogger logger;

    public SmartHomeController(DeviceRegistry registry, StateChangeLogger logger) {
        this.registry = registry;
        this.logger = logger;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDevice(@PathVariable String id) {
        SmartDevice d = registry.getById(id);
        if (d == null) {
            throw new IllegalArgumentException("Device not found: " + id);
        }

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

    @PostMapping("/{id}/on")
    public String turnOn(@PathVariable String id) {
        SmartDevice device = requireDevice(id);
        device.turnOn();
        return "Device " + id + " turned ON";
    }

    @PostMapping("/{id}/off")
    public String turnOff(@PathVariable String id) {
        SmartDevice device = requireDevice(id);
        device.turnOff();
        return "Device " + id + " turned OFF";
    }

    @PostMapping("/{id}/level/{level}")
    public String setLevel(@PathVariable String id, @PathVariable int level) {
        SmartDevice device = requireDevice(id);
        device.setLevel(level);
        return "Device " + id + " level set to " + level;
    }

    private SmartDevice requireDevice(String id) {
        SmartDevice d = registry.getById(id);
        if (d == null) {
            throw new IllegalArgumentException("Device not found: " + id);
        }
        return d;
    }

    @GetMapping
    public List<LinkedHashMap<String, Object>> allDevices() {
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


    @GetMapping("/log")
    public List<StateChange> log() {
        return logger.getChanges();
    }
}