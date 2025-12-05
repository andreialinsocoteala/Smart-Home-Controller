package com.example.smarthome.controller;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.service.SmartHomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DevicePageController {

    private final SmartHomeService service;

    public DevicePageController(SmartHomeService service) {
        this.service = service;
    }

    @GetMapping
    public String listDevices(Model model) {
        List<SmartDevice> devices = service.getAllDevices();
        model.addAttribute("devices", devices);
        return "devices";
    }

    @PostMapping("/{id}/on")
    public String turnOn(@PathVariable String id) {
        service.turnOn(id);
        return "redirect:/devices";
    }

    @PostMapping("/{id}/off")
    public String turnOff(@PathVariable String id) {
        service.turnOff(id);
        return "redirect:/devices";
    }

    @PostMapping("/{id}/level")
    public String setLevel(
            @PathVariable String id,
            @RequestParam("level") int level
    ) {
        service.setLevel(id, level);
        return "redirect:/devices";
    }

    @GetMapping("/logs")
    public String viewLogs(Model model) {
        model.addAttribute("logs", service.getLog());
        return "logs";
    }
}
