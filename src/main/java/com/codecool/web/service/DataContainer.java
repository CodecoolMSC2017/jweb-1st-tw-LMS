package com.codecool.web.service;


import com.codecool.web.model.Courses;
import com.codecool.web.model.Mentor;
import com.codecool.web.model.Student;
import com.codecool.web.model.User;

import java.util.List;

public class DataContainer {
    private List<User> users;
    private List<Courses> coursesList;

    public List<User> getUsersList() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }


    public List<Courses> getCoursesList() {
        return coursesList;
    }


    public void addCourse(Courses courses) {
        coursesList.add(courses);
    }


}
