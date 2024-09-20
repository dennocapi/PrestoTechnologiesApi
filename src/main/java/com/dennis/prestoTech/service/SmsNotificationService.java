package com.dennis.prestoTech.service;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService {

    public void sendSms(String phoneNumber, String message) {
        // Mock SMS sending (print to console)
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
