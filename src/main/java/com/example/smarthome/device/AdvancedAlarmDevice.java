package com.example.smarthome.device;

public class AdvancedAlarmDevice implements SmartDevice {

    private final String id;
    private final String name;

    private DeviceStatus status = DeviceStatus.OFF;
    private int sensitivity = 5;

    public AdvancedAlarmDevice(String id, String name, int initialSensitivity) {
        this.id = id;
        this.name = name;
        this.sensitivity = initialSensitivity;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override
    public void turnOn() {
        status = DeviceStatus.ON;
        System.out.println(name + " ARMED, sensitivity=" + sensitivity);
    }

    @Override
    public void turnOff() {
        status = DeviceStatus.OFF;
        System.out.println(name + " DISARMED");
    }

    @Override
    public void setLevel(int level) {
        this.sensitivity = level;
        System.out.println(name + " sensitivity=" + sensitivity);
    }

    @Override public DeviceStatus getStatus() { return status; }
    @Override public Integer getLevel() { return sensitivity; }
}
