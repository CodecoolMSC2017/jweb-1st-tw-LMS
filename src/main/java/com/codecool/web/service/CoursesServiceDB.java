package com.codecool.web.service;

import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceDB {


    public List<Course> getCourses(CourseDao courseDao) throws SQLException {
        return courseDao.findAll();
    }

    void addNewCourse(String title, String description, CourseDao courseDao) throws SQLException {
        courseDao.addCourse(title,description);
    }

    void addNewAssignment(String title, String description, int maxPoints, CourseDao courseDao) throws SQLException {
        courseDao.addAssignment(title,description,maxPoints);
    }

    void editCourse(int id, String title,String desc, boolean activity, CourseDao courseDao) throws SQLException {
        courseDao.editCourse(id,title,desc,activity);
    }

    Course getCourse(int courseId, CourseDao courseDao) throws SQLException {
        return courseDao.findById(courseId);
    }

    List<Course> availableCourses(CourseDao courseDao) {
        return null;
    }

    void removeCourse(int id, CourseDao courseDao) throws SQLException {
        courseDao.remove(id);
    }

    //TODO It uses the editCourse method until the editAssignment method will be implemented
    void editAssignment(int id, String title,String desc,int newMaxPt, boolean activity, String submission, CourseDao courseDao) throws SQLException {
        courseDao.editCourse(id,title,desc,activity);
    }
}

