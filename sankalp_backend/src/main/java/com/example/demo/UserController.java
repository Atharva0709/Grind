package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerRequest) {
        String email = registerRequest.get("email");
        String password = registerRequest.get("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        if (userRepository.findByEmail(email) != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);

        // Send welcome email
        try {
            emailService.sendWelcomeEmail(email);
        } catch (Exception e) {
            System.err.println("Welcome email failed: " + e.getMessage());
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful. Please check your email.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        User user = userRepository.findByEmail(email);

        if (user == null) {
            // Don't reveal if email exists for security
            return ResponseEntity.ok("If email exists, reset link has been sent");
        }

        // Generate reset token valid for 1 hour
        String resetToken = UUID.randomUUID().toString();
        long expiryTime = System.currentTimeMillis() + (60 * 60 * 1000); // 1 hour

        user.setResetToken(resetToken);
        user.setResetTokenExpiry(expiryTime);
        userRepository.save(user);

        // Send reset email
        String resetLink = "http://localhost:8000/reset-password.html?token=" + resetToken;
        try {
            emailService.sendPasswordResetEmail(email, resetToken, resetLink);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send reset email: " + e.getMessage());
        }

        return ResponseEntity.ok("Password reset link sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        User user = userRepository.findAll().stream()
                .filter(u -> u.getResetToken() != null && u.getResetToken().equals(token))
                .findFirst()
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(400).body("Invalid reset token");
        }

        // Check if token is expired
        if (System.currentTimeMillis() > user.getResetTokenExpiry()) {
            return ResponseEntity.status(400).body("Reset token has expired");
        }

        // Update password
        user.setPassword(newPassword);
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successfully. You can now login.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/streak")
    public ResponseEntity<?> updateStreak(@PathVariable Long id, @RequestParam int streak) {
        return userRepository.findById(id).map(user -> {
            user.setStreak(streak);
            userRepository.save(user);
            return ResponseEntity.ok("Streak updated");
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/discipline")
    public ResponseEntity<?> updateDiscipline(@PathVariable Long id, @RequestParam int score) {
        return userRepository.findById(id).map(user -> {
            user.setDisciplineScore(score);
            userRepository.save(user);
            return ResponseEntity.ok("Discipline updated");
        }).orElse(ResponseEntity.notFound().build());
    }
}

