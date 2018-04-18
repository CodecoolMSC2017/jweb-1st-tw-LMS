package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceTwo {


    List<User> getUsers(UserDao userDao) throws SQLException;


    void addNewUser(User newUser);


    User getUser(String name);


    void register(String name, String email, String password, boolean isMentor, UserDao userDao) throws AlreadyExistsException, NotValidEmailException, SQLException;


    User getUserById(int id, UserDao userDao) throws SQLException;

}


