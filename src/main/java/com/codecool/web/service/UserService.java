package com.codecool.web.service;

import com.codecool.web.model.Student;
import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public List<User> getUsers(){
        return users;
    }

    public void addNewUser(User newUser) {
        users.add(newUser);
    }

    public User getUser(String name) {
        User loginUser = null;
        for(User user :users) {
            if(user.getName().equals(name)) {
                loginUser = user;
            }
        }
        return loginUser;
    }

    public boolean register(String name, String email, String password) {
        for (User user: users) {
            if(!user.getName().equals(name)) {
                User newUser = new Student(generateId(), name, email, password, false, 0);
                users.add(newUser);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int generateId() {
        Random rand = new Random();
        int n = rand.nextInt(10000);
        return n;
    }

    public boolean authenticateUser(String name,String password) {
        for(User user :users) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
