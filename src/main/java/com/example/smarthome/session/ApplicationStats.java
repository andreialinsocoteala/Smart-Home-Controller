package com.example.smarthome.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@ApplicationScope
public class ApplicationStats {

    private final AtomicInteger totalActions = new AtomicInteger();
    private final AtomicInteger totalOnCommands = new AtomicInteger();
    private final AtomicInteger totalOffCommands = new AtomicInteger();

    public void registerOn() {
        totalActions.incrementAndGet();
        totalOnCommands.incrementAndGet();
    }

    public void registerOff() {
        totalActions.incrementAndGet();
        totalOffCommands.incrementAndGet();
    }

    public void registerLevelChange() {
        totalActions.incrementAndGet();
    }

    public int getTotalActions() {
        return totalActions.get();
    }

    public int getTotalOnCommands() {
        return totalOnCommands.get();
    }

    public int getTotalOffCommands() {
        return totalOffCommands.get();
    }
}
