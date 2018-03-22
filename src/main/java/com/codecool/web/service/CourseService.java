package com.codecool.web.service;

import com.codecool.web.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    void addNewCourse(Course newCourse);

    Course getCourse(int courseid);

    List<Course> availableCourses();
}
