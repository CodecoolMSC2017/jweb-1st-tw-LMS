package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;
import com.codecool.web.model.Task;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private List<Course> courses;

    public CourseServiceImpl() {
        courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addNewCourse(String title, String description) {
        Course course = new Course(title, description);
        courses.add(course);
    }

    public Course getCourse(int courseid) {
        for (Course c : courses) {
            if (c.getId() == courseid) {
                return c;
            }
        }
        return null;
    }

    public List<Course> availableCourses() {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getActive()) {
                result.add(course);
            }
        }
        return result;
    }

    public void removeCourse(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                courses.remove(course);
            }
        }
    }

    public Task getTask(int courseId, int taskId) {
        List<Task> tasks = getCourse(courseId).getTasks();

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks(int courseId) {
        return getCourse(courseId).getTasks();
    }

    public void deleteTask(int courseId, int taskId) {
        List<Task> tasks = getCourse(courseId).getTasks();
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);
            }
        }
    }

    public void addTask(int courseId, String title, String description, String type) {
        List<Task> tasks = getCourse(courseId).getTasks();
        Task newTask = null;
        if(type.equals("task")) {
            newTask = new Task(title, description);
        }else if(type.equals("assignment")) {
            newTask = new Assignment(title, description);
        }
        tasks.add(newTask);
    }

    public Course setPublicity(int courseId){
        getCourse(courseId).setActivity();
        return null;
    }
}
