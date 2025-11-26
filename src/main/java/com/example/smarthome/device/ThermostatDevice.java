package com.example.smarthome.device;

public class ThermostatDevice implements SmartDevice {

    private final String id;
    private final String name;

    private DeviceStatus status = DeviceStatus.OFF;
    private int temperature = 21;

    public ThermostatDevice(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override
    public void turnOn() {
        status = DeviceStatus.ON;
        System.out.println(name + " ON at " + temperature + "°C");
    }

    @Override
    public void turnOff() {
        status = DeviceStatus.OFF;
        System.out.println(name + " OFF");
    }

    @Override
    public void setLevel(int level) {
        this.temperature = level;
        System.out.println(name + " temperature=" + temperature + "°C");
    }

    @Override public DeviceStatus getStatus() { return status; }
    @Override public Integer getLevel() { return temperature; }
}
