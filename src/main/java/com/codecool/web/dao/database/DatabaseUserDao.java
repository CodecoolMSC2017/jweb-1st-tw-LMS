package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT id, name, email, password, permission FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    public User findByName(String name) throws SQLException {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        String sql = "SELECT id, name, email, password, permission FROM users WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT id, name, email, password, permission   FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    public User registerUser (String name, String email, String password, boolean permission) throws SQLException{
        if (name == null || "".equals(name) || email == null || "".equals(email) || password == null || "".equals(password)) {
            throw new IllegalArgumentException("Fields can not be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (name, email, password, permission) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBoolean(4, permission);
            statement.executeUpdate();
            int id = fetchGeneratedId(statement);
            connection.commit();
            return new User(id, name, email, password, permission);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void editUser(String email, String password, int id) throws SQLException {
        if (email == null || "".equals(email) || password == null || "".equals(password)) {
            throw new IllegalArgumentException("Fields can not be empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET email = ?, password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Boolean permission = resultSet.getBoolean("permission");
        return new User(id, name, email, password, permission);
    }
}
