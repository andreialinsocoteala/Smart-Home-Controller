package com.example.smarthome.controller;

import com.example.smarthome.logging.StateChange;
import com.example.smarthome.service.SmartHomeService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
public class SmartHomeController {

    private final SmartHomeService service;

    public SmartHomeController(SmartHomeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDevice(@PathVariable String id) {
        return service.getDeviceDetails(id);
    }

    @PostMapping("/{id}/on")
    public String turnOn(@PathVariable String id) {
        return service.turnOn(id);
    }

    @PostMapping("/{id}/off")
    public String turnOff(@PathVariable String id) {
        return service.turnOff(id);
    }

    @PostMapping("/{id}/level/{level}")
    public String setLevel(@PathVariable String id, @PathVariable int level) {
        return service.setLevel(id, level);
    }

    @GetMapping
    public List<LinkedHashMap<String, Object>> allDevices() {
        return service.getAllDevices();
    }

    @GetMapping("/log")
    public List<StateChange> log() {
        return service.getLog();
    }
}
