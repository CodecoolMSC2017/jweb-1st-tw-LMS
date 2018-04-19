package com.codecool.web.service;

import com.codecool.web.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    void addNewCourse(String title, String description);

    void addNewAssignment(String title, String description, int maxPoints);

    void editCourse(int id, String title,String desc, boolean activity);

    Course getCourse(int courseid);

    List<Course> availableCourses();

    void removeCourse(int id);

    void editAssignment(int id, String title,String desc,int newMaxPt, boolean activity, String submission);
}


