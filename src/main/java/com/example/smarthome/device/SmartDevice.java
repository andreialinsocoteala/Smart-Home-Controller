package com.example.smarthome.device;

public interface SmartDevice {
    String getId();
    String getName();
    void turnOn();
    void turnOff();
    void setLevel(int level);
    DeviceStatus getStatus();

    default Integer getLevel() {
        return null;
    }
}