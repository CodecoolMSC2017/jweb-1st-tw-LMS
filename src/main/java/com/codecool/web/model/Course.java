package com.codecool.web.model;

import java.util.List;

public class Course {
    private List<Task> tasks;
    private List<Lesson> lessons;

    public Course(List<Task> tasks, List<Lesson> lessons) {
        this.tasks = tasks;
        this.lessons = lessons;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
