package com.example.smarthome.device;

public class LightDevice implements SmartDevice {

    private final String id;
    private final String name;

    private DeviceStatus status = DeviceStatus.OFF;
    private int level;

    public LightDevice(String id, String name, int initialLevel) {
        this.id = id;
        this.name = name;
        this.level = initialLevel;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public void turnOn() {
        status = DeviceStatus.ON;
        if (level == 0) {
            level = 50;
        }
        System.out.println(name + " ON, level=" + level);
    }

    @Override
    public void turnOff() {
        status = DeviceStatus.OFF;
        if(level != 0) {
            level = 0;
        }
        System.out.println(name + " OFF");
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
        if(level != 0) {
            status = DeviceStatus.ON;
        } else {
            status = DeviceStatus.OFF;
        }
        System.out.println(name + " level=" + level);
    }

    @Override
    public DeviceStatus getStatus() { return status; }

    @Override
    public Integer getLevel() { return level; }

}