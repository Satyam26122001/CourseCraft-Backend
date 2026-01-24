package com.onlineCourse.eduhub.service;

import com.onlineCourse.eduhub.dto.auth.*;

public interface AuthService {
    void signup(SignupRequest request);
    LoginResponse login(LoginRequest request);
}