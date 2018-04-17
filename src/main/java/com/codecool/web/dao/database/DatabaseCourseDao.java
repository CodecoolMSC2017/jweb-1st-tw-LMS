package com.codecool.web.dao.database;

import com.codecool.web.model.Course;
import com.codecool.web.dao.CourseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCourseDao extends AbstractDao implements CourseDao{

    public DatabaseCourseDao (Connection connection) {
        super(connection);
    }

    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * from courses";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            List<Course> courses = new ArrayList<>();
            while(resultSet.next()) {
                courses.add(fetchCourse(resultSet));
            }
            return courses;
        }
    }

    private Course fetchCourse(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        boolean isActive = resultSet.getBoolean("is_active");
        return new Course(id, name, description, isActive);
    }

    public Course findById(int id) throws SQLException {
        if(id == 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * from courses WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchCourse(resultSet);
                }
            }
        }
        return null;
    }

    public void add(String name, String description, boolean isActive) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO courses (name, description, is_active) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setBoolean(3, isActive);
            int id = fetchGeneratedId(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void remove(int id) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "DELETE * from courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void editCourse(int id, String name, String description, boolean isActive) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "Update * from courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setBoolean(4, isActive);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

}
