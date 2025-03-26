package com.example.msumico.scheduler;

import com.example.msumico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UmicoScheduler {
    private final UserService userService;

    @Scheduled(cron = "15 55 * * * *")
    public void sendBirthdayMessage() {
        userService.getUsersByBirthday().forEach(
                item -> {
                    System.out.println(item.getName() + " " + "HBD!");
                }
        );

    }
}