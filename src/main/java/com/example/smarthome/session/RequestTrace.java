package com.example.smarthome.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.Instant;
import java.util.UUID;

@Component
@RequestScope
public class RequestTrace {

    private final String requestId = UUID.randomUUID().toString();
    private final Instant createdAt = Instant.now();

    public String getRequestId() {
        return requestId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
