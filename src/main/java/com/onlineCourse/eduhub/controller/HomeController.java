package com.onlineCourse.eduhub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        log.info("Home API hit");
        return Map.of(
                "success", true,
                "message", "Welcome to EduHub - Your Gateway to Knowledge!"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("status", "UP");
    }
}