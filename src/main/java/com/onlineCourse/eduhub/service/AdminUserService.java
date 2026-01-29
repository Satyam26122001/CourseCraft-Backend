package com.onlineCourse.eduhub.service;

import com.onlineCourse.eduhub.dto.user.UpdateProfileRequest;
import com.onlineCourse.eduhub.dto.user.UserResponse;

import java.util.List;

public interface AdminUserService {

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Integer userId, UpdateProfileRequest request);

    void deleteUser(Integer userId);
    
    void updateUserRole(Integer userId, String role);
}