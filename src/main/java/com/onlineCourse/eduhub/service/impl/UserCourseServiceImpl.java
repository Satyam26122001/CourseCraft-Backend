package com.onlineCourse.eduhub.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineCourse.eduhub.entity.Course;
import com.onlineCourse.eduhub.entity.Enrollment;
import com.onlineCourse.eduhub.entity.User;
import com.onlineCourse.eduhub.repository.CourseRepository;
import com.onlineCourse.eduhub.repository.EnrollmentRepository;
import com.onlineCourse.eduhub.repository.UserRepository;
import com.onlineCourse.eduhub.security.SecurityUtil;
import com.onlineCourse.eduhub.service.UserCourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCourseServiceImpl implements UserCourseService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final SecurityUtil securityUtil;

    @Override
    public List<Course> getMyCourses() {

        String email = securityUtil.getCurrentUserEmail()
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Enrollment> enrollments = enrollmentRepository.findByUser(user);

        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

    @Override
    public void enrollInCourse(Integer courseId) {

        String email = securityUtil.getCurrentUserEmail()
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
            throw new RuntimeException("User already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .build();

        enrollmentRepository.save(enrollment);
    }
    
    @Override
    public void unenrollFromCourse(Integer courseId) {

        String email = securityUtil.getCurrentUserEmail()
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enrollment enrollment = enrollmentRepository
                .findByUserIdAndCourseId(user.getId(), courseId)
                .orElseThrow(() -> new RuntimeException("User is not enrolled in this course"));

        enrollmentRepository.delete(enrollment);
    }
}