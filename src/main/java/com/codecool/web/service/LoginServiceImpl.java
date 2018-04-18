package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService{

    private final UserDao userDao;

    public LoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String name, String password) throws SQLException, LoginException {
        User user = userDao.findByName(name);
        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginException("Incorrect log in");
        }
        return user;
    }
}
