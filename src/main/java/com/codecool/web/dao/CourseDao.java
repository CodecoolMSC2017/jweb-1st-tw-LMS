package com.codecool.web.dao;

import com.codecool.web.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    List<Course> findAll() throws SQLException ;

    Course findById(int id) throws SQLException ;

    void add(String name, String description, boolean isActive) throws SQLException ;

    void remove(int id) throws SQLException ;

    void editCourse(int id, String name, String description, boolean isActive) throws SQLException ;
}
