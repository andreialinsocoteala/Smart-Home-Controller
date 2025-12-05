package com.example.smarthome.controller;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.service.SmartHomeService;
import com.example.smarthome.session.ApplicationStats;
import com.example.smarthome.session.UserSessionPreferences;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/devices")
public class DevicePageController {

    private final SmartHomeService service;
    private final UserSessionPreferences sessionPreferences;
    private final ApplicationStats applicationStats;

    public DevicePageController(SmartHomeService service, UserSessionPreferences sessionPreferences
    , ApplicationStats applicationStats) {
        this.service = service;
        this.sessionPreferences = sessionPreferences;
        this.applicationStats = applicationStats;
    }

    @GetMapping
    public String listDevices(Model model) {
        model.addAttribute("devices", service.getAllDevices());
        model.addAttribute("sessionActions", sessionPreferences.getActionsCount());
        model.addAttribute("sessionLastDevice", sessionPreferences.getLastDeviceId());

        model.addAttribute("globalTotalActions", applicationStats.getTotalActions());
        model.addAttribute("globalOnCommands", applicationStats.getTotalOnCommands());
        model.addAttribute("globalOffCommands", applicationStats.getTotalOffCommands());
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

    @GetMapping("/session-info")
    @ResponseBody
    public Map<String, Object> sessionInfo() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("actionsCount", sessionPreferences.getActionsCount());
        map.put("lastDeviceId", sessionPreferences.getLastDeviceId());

        map.put("globalTotalActions", applicationStats.getTotalActions());
        map.put("globalOnCommands", applicationStats.getTotalOnCommands());
        map.put("globalOffCommands", applicationStats.getTotalOffCommands());

        return map;
    }

}
