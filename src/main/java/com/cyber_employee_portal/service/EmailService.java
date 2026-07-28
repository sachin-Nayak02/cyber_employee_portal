package com.cyber_employee_portal.service;

public interface EmailService {
    void sendOtpEmail(String toEmail, String otp);
}