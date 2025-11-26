package com.example.smarthome.logging;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StateChangeLogger {

    private final List<StateChange> changes = new ArrayList<>();

    public void logChange(String deviceId, String action) {
        StateChange change = new StateChange(deviceId, action);
        changes.add(change);
        System.out.println("AOP LOG: " + deviceId + " -> " + action + " at " + change.getTimestamp());
    }

    public List<StateChange> getChanges() {
        return Collections.unmodifiableList(changes);
    }


}
