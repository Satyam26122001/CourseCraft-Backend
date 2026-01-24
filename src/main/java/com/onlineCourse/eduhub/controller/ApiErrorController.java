package com.onlineCourse.eduhub.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ApiErrorController implements ErrorController {

	
	@RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {

        Object statusObj = request.getAttribute("jakarta.servlet.error.status_code");
        int status = statusObj != null ? (int) statusObj : 500;

        Object messageObj = request.getAttribute("jakarta.servlet.error.message");
        String message = messageObj != null ? messageObj.toString() : "Unexpected error";

        return ResponseEntity.status(status).body(Map.of(
                "timestamp", Instant.now().toString(),
                "success", false,
                "status", status,
                "message", message
        ));
    }
	
}
