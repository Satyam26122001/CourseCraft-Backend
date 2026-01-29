package com.onlineCourse.eduhub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineCourse.eduhub.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("""
        SELECT c FROM Course c
        WHERE lower(c.courseName) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.courseDescription) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.trainer) LIKE lower(concat('%', :keyword, '%'))
        ORDER BY c.rating DESC
    """)
    List<Course> searchCourses(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(c) FROM Course c")
    long countCourses();
    
    @Query("SELECT MAX(c.publishedAt) FROM Course c")
    LocalDateTime lastCoursePublished();
}