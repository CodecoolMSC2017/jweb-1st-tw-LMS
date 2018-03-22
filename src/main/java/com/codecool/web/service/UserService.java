package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.List;

public interface UserService {


    List<User> getUsers();

    void addNewUser(User newUser);

    User getUser(String name);

    String register(String name, String email, String password);

    int generateId();

    boolean authenticateUser(String name,String password);

    User getUserById(int id);
}
