package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userRepository.save(newUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        return ResponseEntity.ok(response);
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