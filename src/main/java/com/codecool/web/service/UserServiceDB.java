package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceDB implements UserServiceTwo {

    @Override
    public List<User> getUsers(UserDao userDao) throws SQLException {
       return userDao.findAll();
    }

    @Override
    public User getUser(String name,UserDao userDao) throws SQLException {
        return userDao.findByName(name);
    }

    @Override
    public void register(String name, String email, String password, boolean isMentor, UserDao userDao) throws AlreadyExistsException, NotValidEmailException, SQLException {
        userDao.registerUser(name,email,password,isMentor);
    }
    

    @Override
    public User getUserById(int id, UserDao userDao) throws SQLException {
        return userDao.findById(id);
    }
}
