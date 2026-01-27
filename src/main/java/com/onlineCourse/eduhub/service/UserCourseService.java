package com.onlineCourse.eduhub.service;

import java.util.List;
import com.onlineCourse.eduhub.entity.Course;

public interface UserCourseService {

    List<Course> getMyCourses();

    void enrollInCourse(Integer courseId);

    void unenrollFromCourse(Integer courseId); // 👈 NEW
}