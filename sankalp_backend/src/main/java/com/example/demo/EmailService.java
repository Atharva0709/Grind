package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String email, String resetToken, String resetLink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // FIXED: Removed the stray backslash and added a proper email string
            message.setFrom(fromEmail); 
            message.setTo(email);
            message.setSubject("GRIND - Password Reset Request");
            message.setText("Hello,\n\n" +
                    "You requested a password reset for your GRIND account.\n\n" +
                    "Click the link below to reset your password:\n" +
                    resetLink + "\n\n" +
                    "Or use this token: " + resetToken + "\n\n" +
                    "This link will expire in 1 hour.\n\n" +
                    "If you did not request this, please ignore this email.\n\n" +
                    "Best regards,\n" +
                    "GRIND Team");
            
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }

    public void sendWelcomeEmail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // FIXED: Removed the stray backslash here as well
            message.setFrom(fromEmail); 
            message.setTo(email);
            message.setSubject("Welcome to GRIND - Your Productivity System");
            message.setText("Welcome to GRIND!\n\n" +
                    "Your account has been successfully created. " +
                    "Get ready to boost your productivity and achieve your goals.\n\n" +
                    "Login here to get started.\n\n" +
                    "Best regards,\n" +
                    "GRIND Team");
            
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send welcome email: " + e.getMessage());
        }
    }
}