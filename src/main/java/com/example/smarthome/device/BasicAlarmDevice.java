package com.example.smarthome.device;

public class BasicAlarmDevice implements SmartDevice {

    private final String id;
    private final String name;

    private DeviceStatus status = DeviceStatus.OFF;

    public BasicAlarmDevice(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override
    public void turnOn() {
        status = DeviceStatus.ON;
        System.out.println(name + " ARMED");
    }

    @Override
    public void turnOff() {
        status = DeviceStatus.OFF;
        System.out.println(name + " DISARMED");
    }

    @Override
    public void setLevel(int level) {
        System.out.println(name + " sensitivity=" + level);
    }

    @Override public DeviceStatus getStatus() { return status; }
}
