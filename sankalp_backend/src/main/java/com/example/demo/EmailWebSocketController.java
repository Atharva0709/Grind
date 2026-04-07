package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@EnableScheduling
public class EmailWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final String[] SENDERS = {"Sarah Jenkins", "Growth Bot", "HR Portal", "System Alert", "Team Lead"};
    private static final String[] SUBJECTS = {"Regarding the new workflow", "Server capacity reached 92%", "Monthly timesheet", "Your deployment passed", "Let's sync up"};
    private static final String[] BADGES = {"Urgent Alert", "Pending", "System Task", "Action Required"};

    @Scheduled(fixedRate = 15000) // send every 15 seconds
    public void simulateIncomingEmail() {
        Random rand = new Random();
        Map<String, String> emailMsg = new HashMap<>();
        emailMsg.put("sender", SENDERS[rand.nextInt(SENDERS.length)]);
        emailMsg.put("subject", SUBJECTS[rand.nextInt(SUBJECTS.length)]);
        emailMsg.put("badge", BADGES[rand.nextInt(BADGES.length)]);
        emailMsg.put("id", String.valueOf(System.currentTimeMillis()));
        
        messagingTemplate.convertAndSend("/topic/emails", emailMsg);
    }
}
