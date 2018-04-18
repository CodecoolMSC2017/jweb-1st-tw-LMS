package com.codecool.web.service;

import com.codecool.web.model.User;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public interface LoginService {

    User loginUser(String name, String password) throws SQLException, LoginException;
}
