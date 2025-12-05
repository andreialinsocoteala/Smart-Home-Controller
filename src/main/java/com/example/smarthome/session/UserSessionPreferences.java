package com.example.smarthome.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSessionPreferences {

    private int actionsCount = 0;
    private String lastDeviceId;

    public void registerAction(String deviceId) {
        actionsCount++;
        lastDeviceId = deviceId;
    }

    public int getActionsCount() {
        return actionsCount;
    }

    public String getLastDeviceId() {
        return lastDeviceId;
    }
}
