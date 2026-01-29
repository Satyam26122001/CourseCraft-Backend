package com.onlineCourse.eduhub.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineCourse.eduhub.dto.admin.AdminDashboardResponse;
import com.onlineCourse.eduhub.repository.CourseRepository;
import com.onlineCourse.eduhub.repository.EnrollmentRepository;
import com.onlineCourse.eduhub.repository.UserRepository;
import com.onlineCourse.eduhub.service.AdminDashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional(readOnly = true)
    @Cacheable("admin-dashboard")
    public AdminDashboardResponse getDashboardStats() {

        long totalCourses = courseRepo.countCourses();       // or courseRepo.count()
        long totalEnrollments = enrollmentRepo.countEnrollments(); // or enrollmentRepo.count()
        long totalStudents = userRepo.countUsers();          // or userRepo.count()

        Long revenue = enrollmentRepo.totalRevenue();
        
        System.out.println("Calculated total revenue: " + revenue);
        
        BigDecimal totalRevenue =
                revenue == null ? BigDecimal.ZERO : BigDecimal.valueOf(revenue);
        
        Instant lastEnrollment = enrollmentRepo.lastEnrollment();
        
        LocalDateTime lastCoursePublished = courseRepo.lastCoursePublished();

        LocalDateTime lastUserRegistered = userRepo.lastUserRegistered();

        return AdminDashboardResponse.from(
            totalCourses,
            totalEnrollments,
            totalStudents,
            totalRevenue,
            lastEnrollment,
            lastCoursePublished,
            lastUserRegistered
        );
    }
}