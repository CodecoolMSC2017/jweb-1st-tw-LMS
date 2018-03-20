package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

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
}
