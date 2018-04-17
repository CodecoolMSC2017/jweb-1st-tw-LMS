package com.codecool.web.dao;

import com.codecool.web.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> findAll() throws SQLException;

    User findByEmail(String email) throws SQLException;

    User findByName(String name) throws SQLException;

    User findById(int id) throws SQLException;

    User registerUser (String name, String email, String password, boolean permission) throws SQLException;

    void editUser(String email, String password, int id) throws SQLException;
}
