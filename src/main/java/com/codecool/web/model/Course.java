package com.codecool.web.model;

import java.util.List;
import java.util.Random;

public class Course {
    private int id;
    private List<Task> tasks;
    private List<Lesson> lessons;

    public Course(List<Task> tasks, List<Lesson> lessons) {
        this.id = new Random().nextInt(100000);
        this.tasks = tasks;
        this.lessons = lessons;
    }

    public int getId() { return id; }

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
