package com.example.smarthome.controller;

import com.example.smarthome.session.UserSessionContext;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final ObjectProvider<UserSessionContext> sessionProvider;

    public SessionController(ObjectProvider<UserSessionContext> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @GetMapping("/new")
    public String createNewSession(@RequestParam String username) {

        UserSessionContext session = sessionProvider.getObject();

        session.setUsername(username);

        return "Created new UserSessionContext: " + session;
    }
}