package com.example.smarthome.logging;

import java.time.LocalDateTime;

public class StateChange {

    private String deviceId;
    private String action;
    private LocalDateTime timestamp;

    public StateChange(String deviceId, String action) {
        this.deviceId = deviceId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
