package com.example.smarthome.logging;

import com.example.smarthome.device.SmartDevice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final StateChangeLogger logger;

    @Autowired
    public LoggingAspect(StateChangeLogger logger) {
        this.logger = logger;
    }

    @AfterReturning(
            "execution(* com.example.smarthome.device.SmartDevice.turnOn(..)) || " +
                    "execution(* com.example.smarthome.device.SmartDevice.turnOff(..)) || " +
                    "execution(* com.example.smarthome.device.SmartDevice.setLevel(..))"
    )
    public void logChange(JoinPoint jp) {
        Object target = jp.getTarget();
        if (target instanceof SmartDevice device) {
            String action = jp.getSignature().getName();
            logger.logChange(device.getId(), action);
        }
    }
}
