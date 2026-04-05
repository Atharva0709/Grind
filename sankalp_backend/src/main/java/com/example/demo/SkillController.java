package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
public class SkillController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getSkills(@PathVariable Long userId) {
        return ResponseEntity.ok(skillRepository.findByUserId(userId));
    }
    
    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skill) {
        return ResponseEntity.ok(skillRepository.save(skill));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillRepository.findById(id).map(s -> {
            s.setName(skill.getName());
            s.setProficiency(skill.getProficiency());
            s.setCategory(skill.getCategory());
            s.setHoursSpent(skill.getHoursSpent());
            return ResponseEntity.ok(skillRepository.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        skillRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
