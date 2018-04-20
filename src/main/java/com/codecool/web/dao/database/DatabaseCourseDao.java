package com.codecool.web.dao.database;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;
import com.codecool.web.dao.CourseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCourseDao extends AbstractDao implements CourseDao{

    public DatabaseCourseDao (Connection connection) {
        super(connection);
    }

    public void setActivity(int id, boolean isActive) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE courses SET is_active = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setBoolean(1, isActive);
            statement.setInt(2,id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public List<Course> findAllCourse() throws SQLException {
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

    public Course findCourseById(int id) throws SQLException {
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

    public Course findCourseByName(String name) throws SQLException {
        Course result = null;
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        String sql = "select * from courses\n" +
                "where name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = fetchCourse(resultSet);
                }

            }
        }
        return result;
    }

    public void addCourse(String name, String description) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO courses (name, description, is_active) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setBoolean(3, false);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);

            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void removeCourse(int id) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "DELETE FROM courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            statement.executeUpdate();
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
        String sql = "UPDATE courses " +
                "SET name = ?, description = ?, is_active = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(4, id);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setBoolean(3, isActive);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public int getId(String name) throws SQLException {
        int id = 0;
        String sql = "select id from courses\n" +
                "where courses.name= ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

    public void addAssignment(String name, String description, int maxPoint) throws SQLException {
        addCourse(name, description);

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql ="INSERT INTO assignments (course_id, maxpoints) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            int id = fetchGeneratedId(statement);
            statement.setInt(1, getId(name));
            statement.setInt(1,maxPoint);
            executeInsert(statement);

            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void editAssignment(int id, String name, String description, int maxPoint) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE assignments " +
                "SET name = ?, description = ?, maxpoint = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setInt(4, maxPoint);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void removeAssignment(int id) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "DELETE FROM submissions WHERE id = ?" +
                "DELETE FROM assignments WHERE id = ?" +
                "DELETE FROM courses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public Assignment findAssignmentById(int id) throws SQLException {
        if(id == 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * from assignments WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchAssignment(resultSet);
                }
            }
        }
        return null;
    }

    public List<Assignment> findAllAssignment() throws SQLException {
        String sql = "SELECT * from assignments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Assignment> assignments = new ArrayList<>();
            while(resultSet.next()) {
                assignments.add(fetchAssignment(resultSet));
            }
            return assignments;
        }
    }

    private Assignment fetchAssignment(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int maxpoints = resultSet.getInt("maxpoints");
        return new Assignment(name, description, maxpoints);
    }
}
