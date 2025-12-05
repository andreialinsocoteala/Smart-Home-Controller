package com.example.smarthome.utils;

public class ErrorDetails {

    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
