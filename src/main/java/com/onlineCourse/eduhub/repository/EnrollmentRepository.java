package com.onlineCourse.eduhub.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineCourse.eduhub.entity.Enrollment;
import com.onlineCourse.eduhub.entity.User;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByUser(User user);
    
    boolean existsByUserIdAndCourseId(Integer userId, Integer courseId);
    
    @Query("""
            select e.course.id
            from Enrollment e
            where e.user.email = :email
        """)
        List<Integer> findEnrolledCourseIds(@Param("email") String email);
    
    Optional<Enrollment> findByUserIdAndCourseId(Integer userId, Integer courseId);
    
    @Query("SELECT COUNT(e) FROM Enrollment e")
    long countEnrollments();

    @Query("""
        SELECT COALESCE(SUM(c.price), 0)
        FROM Enrollment e
        JOIN e.course c
    """)
    Long totalRevenue();

    @Query("SELECT MAX(e.enrolledAt) FROM Enrollment e")
    Instant lastEnrollment();
}