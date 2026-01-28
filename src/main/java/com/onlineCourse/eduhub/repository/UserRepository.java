package com.onlineCourse.eduhub.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlineCourse.eduhub.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT COUNT(u) FROM User u")
    long countUsers();

    @Query("SELECT MAX(u.createdAt) FROM User u")
    LocalDateTime lastUserRegistered();
}
