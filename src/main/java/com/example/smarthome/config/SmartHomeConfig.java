package com.example.smarthome.config;


import com.example.smarthome.device.*;
import com.example.smarthome.registry.DeviceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SmartHomeConfig {

    @Value("${smarthome.alarm.default-level}")
    private int defaultAlarmSensitivity;

    @Value("${smarthome.light.default-level}")
    private int defaultLightLevel;

    @Value("${smarthome.thermostat.default-temperature}")
    private int defaultThermostatTemperature;

    @Bean
    public DeviceRegistry deviceRegistry(List<SmartDevice> devices) {
        return new DeviceRegistry(devices);
    }

    // Lights
    @Bean
    public SmartDevice livingRoomLight() {
        return new LightDevice("light-1", "Living Room Light", defaultLightLevel);
    }

    @Bean
    public SmartDevice kitchenLight() {
        return new LightDevice("light-2", "Kitchen Light", defaultLightLevel);
    }

    // Thermostats
    @Bean
    public SmartDevice mainThermostat() {
        return new ThermostatDevice("thermo-1", "Main Thermostat", defaultThermostatTemperature);
    }

    @Bean
    public SmartDevice guestThermostat() {
        return new ThermostatDevice("thermo-2", "Guest Room Thermostat", defaultThermostatTemperature);
    }

    // Alarms
    @Bean
    public SmartDevice basicAlarm() {
        return new BasicAlarmDevice("alarm-basic", "Basic Alarm");
    }

    @Bean
    public SmartDevice advancedAlarm() {
        return new AdvancedAlarmDevice("alarm-adv-1", "Advanced Alarm 1", defaultAlarmSensitivity);
    }

    @Bean
    public SmartDevice advancedAlarm2() {
        return new AdvancedAlarmDevice("alarm-adv-2", "Advanced Alarm 2", defaultAlarmSensitivity);
    }
}