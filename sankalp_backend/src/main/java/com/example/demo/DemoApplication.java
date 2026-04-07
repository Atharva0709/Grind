package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, TaskRepository taskRepository,
            SkillRepository skillRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User u = new User();
                u.setEmail("chinmay@example.com");
                u.setPassword("pass");
                u.setStreak(0);
                u.setDisciplineScore(0);
                User savedUser = userRepository.save(u);

                Task t1 = new Task();
                t1.setTitle("MISSION");
                t1.setPriority("URGENT");
                t1.setStatus("PENDING");
                t1.setUserId(savedUser.getId());
                taskRepository.save(t1);

                Task t2 = new Task();
                t2.setTitle("MATH 201 PROBLEM SET");
                t2.setPriority("URGENT");
                t2.setStatus("PENDING");
                t2.setUserId(savedUser.getId());
                taskRepository.save(t2);

                Task t3 = new Task();
                t3.setTitle("ALGO EXAM PREP");
                t3.setPriority("NORMAL");
                t3.setStatus("COMPLETED");
                t3.setUserId(savedUser.getId());
                taskRepository.save(t3);

                Skill s1 = new Skill();
                s1.setUserId(savedUser.getId());
                s1.setName("JAVA");
                s1.setProficiency(0);
                s1.setCategory("PROGRAMMING");
                s1.setHoursSpent(0);
                skillRepository.save(s1);

                Skill s2 = new Skill();
                s2.setUserId(savedUser.getId());
                s2.setName("PROBLEM SOLVING");
                s2.setProficiency(0);
                s2.setCategory("SKILLS");
                s2.setHoursSpent(0);
                skillRepository.save(s2);
            }
        };
    }
}
