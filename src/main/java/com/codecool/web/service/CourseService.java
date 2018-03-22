package com.codecool.web.service;

import com.codecool.web.model.Course;
import com.codecool.web.model.Task;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    void addNewCourse(String title, String description);

    Course getCourse(int courseid);

    List<Course> availableCourses();

    void removeCourse(int id);

    Task getTask(int courseId, int taskId);

    void deleteTask(int courseId, int taskId);

    void addTask(int courseId, String title, String description, String type);
}
