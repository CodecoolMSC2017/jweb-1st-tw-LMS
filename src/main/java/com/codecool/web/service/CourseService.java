package com.codecool.web.service;

import com.codecool.web.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getCourses();

    public void addNewCourse(Course newCourse);

    public Course getCourse(int courseid);
}
