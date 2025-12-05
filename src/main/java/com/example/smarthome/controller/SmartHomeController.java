package com.example.smarthome.controller;

import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.logging.StateChange;
import com.example.smarthome.service.SmartHomeService;
import com.example.smarthome.utils.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getDevice(@PathVariable String id) {
        try {
            SmartDevice d = service.getDevice(id);

            HttpHeaders headers = new HttpHeaders();
            headers.add("device-status", d.getStatus().name());

            Map<String, Object> body = toResponse(d);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(body);

        } catch (IllegalArgumentException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> allDevices() {
        List<Map<String, Object>> body = service.getAllDevices()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity
                .ok(body);
    }

    @PostMapping("/{id}/on")
    public ResponseEntity<?> turnOn(@PathVariable String id) {
        try {
            SmartDevice d = service.turnOn(id);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .header("device-status", d.getStatus().name())
                    .body(toResponse(d));

        } catch (IllegalArgumentException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }
    }

    @PostMapping("/{id}/off")
    public ResponseEntity<?> turnOff(@PathVariable String id) {
        try {
            SmartDevice d = service.turnOff(id);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .header("device-status", d.getStatus().name())
                    .body(toResponse(d));

        } catch (IllegalArgumentException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }
    }

    @PostMapping("/{id}/level")
    public ResponseEntity<?> setLevel(
            @PathVariable String id,
            @RequestBody LevelRequest request
    ) {
        try {
            if (request.getLevel() == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ErrorDetails("Level must be provided."));
            }

            SmartDevice d = service.setLevel(id, request.getLevel());

            HttpHeaders headers = new HttpHeaders();
            headers.add("device-status", d.getStatus().name());

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .headers(headers)
                    .body(toResponse(d));

        } catch (IllegalArgumentException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }
    }

    @GetMapping("/log")
    public ResponseEntity<List<StateChange>> log() {
        return ResponseEntity
                .ok(service.getLog());
    }

    private Map<String, Object> toResponse(SmartDevice d) {
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
}
