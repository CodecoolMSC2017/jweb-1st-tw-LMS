package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
    private int id;
    private String name;
    private String desc;
    boolean isActive;
    private List<Task> tasks;

    public Course(String name, String desc) {
        this.id = new Random().nextInt(100000);
        this.name = name;
        this.desc = desc;
        this.isActive = false;
        this.tasks = new ArrayList<>();
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getDesc() { return desc; }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addNewTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(String title) {
        for(Task task : tasks) {
            if(task.getTitle().equals(title)) {
                tasks.remove(task);
            }
        }
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActivity() {
        if(getActive()) {
            isActive = false;
        }
        isActive = true;
    }

}
