package com.codecool.web.dao;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    List<Course> findAllCourse() throws SQLException ;

    Course findCourseById(int id) throws SQLException ;

    void addCourse(String name, String description) throws SQLException ;

    void removeCourse(int id) throws SQLException ;

    void editCourse(int id, String name, String description, boolean isActive) throws SQLException ;

    void addAssignment(String name, String description, int maxPoint) throws SQLException ;

    void editAssignment(int id, String name, String description, int maxPoint) throws SQLException ;

    void removeAssignment(int id) throws SQLException ;

    Assignment findAssignmentById(int id) throws SQLException ;

    List<Assignment> findAllAssignment() throws SQLException ;

    void setActivity(int id, boolean isActive) throws SQLException ;

}
