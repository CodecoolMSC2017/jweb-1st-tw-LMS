package com.codecool.web.service;


import com.codecool.web.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContainer {

    private static DataContainer instance = new DataContainer();
    private List<User> users = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private Map<Integer,List<Assignment>> sentAssignments= new HashMap<>();

    public static DataContainer getInstance() {
        return instance;
    }

    private DataContainer() {
    }

    public List<User> getUsersList() {
        return users;
    }

    public User getUser(String name) {
        for(User user : users) {
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }


    public List<Course> getCoursesList() {
        return courses;
    }


    public void addCourse(Course course) {
        courses.add(course);
    }

    public Map<Integer, List<Assignment>> getSentAssignments() {
        return sentAssignments;
    }


}
