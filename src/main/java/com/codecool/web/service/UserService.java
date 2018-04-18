package com.codecool.web.service;

import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.model.User;

import java.util.List;

public interface UserService {


    List<User> getUsers();

    void addNewUser(User newUser);

    User getUser(String name);

    void register(String name, String email, String password, boolean isMentor) throws AlreadyExistsException, NotValidEmailException;

    int generateId();

    boolean authenticateUser(String name,String password);

    User getUserById(int id);
}
