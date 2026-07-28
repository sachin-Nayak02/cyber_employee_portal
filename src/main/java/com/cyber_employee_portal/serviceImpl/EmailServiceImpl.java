package com.cyber_employee_portal.serviceImpl;

import com.cyber_employee_portal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Cyber Employee Portal - Password Reset OTP");
        message.setText(
                "Your OTP for password reset is: " + otp +
                "\n\nThis OTP is valid for 10 minutes. If you didn't request this, please ignore this email."
        );
        mailSender.send(message);
    }
}