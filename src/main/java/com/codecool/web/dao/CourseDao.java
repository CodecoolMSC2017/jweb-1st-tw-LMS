package com.codecool.web.dao;

import com.codecool.web.model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

interface CourseDao {
    List<Course> findAll() throws SQLException ;

    Course fetchCourse(ResultSet resultSet) throws SQLException ;

    Course findById(int id) throws SQLException ;

    void add(String name, String description, boolean isActive) throws SQLException ;

    void remove(int id) throws SQLException ;

    void editCourse(int id, String name, String description, boolean isActive) throws SQLException ;
}
