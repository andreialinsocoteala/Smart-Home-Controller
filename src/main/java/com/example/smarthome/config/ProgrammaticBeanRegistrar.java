package com.example.smarthome.config;

import com.example.smarthome.device.DeviceStatus;
import com.example.smarthome.device.SmartDevice;
import com.example.smarthome.registry.DeviceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgrammaticBeanRegistrar {

    @Autowired
    private DeviceRegistry deviceRegistry;

    @Bean
    public ApplicationRunner registerExtraDevice(ConfigurableListableBeanFactory beanFactory) {
        return args -> {
            SmartDevice smartPlug = new SmartDevice() {
                @Override public String getId() { return "plug-1"; }
                @Override public String getName() { return "Smart Plug"; }
                @Override public void turnOn() { System.out.println("Smart plug ON"); }
                @Override public void turnOff() { System.out.println("Smart plug OFF"); }
                @Override public void setLevel(int level) { }
                @Override public DeviceStatus getStatus() { return DeviceStatus.ON; }
            };

            beanFactory.registerSingleton("smartPlug", smartPlug);
            deviceRegistry.registerDevice(smartPlug);
            System.out.println("Programmatically registered Smart Plug device");
        };
    }
}
