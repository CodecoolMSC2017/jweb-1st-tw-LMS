package com.codecool.web.service;

import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceDB {


    public List<Course> getCourses(CourseDao courseDao) throws SQLException {
        return courseDao.findAllCourse();
    }

    void addNewCourse(String title, String description, CourseDao courseDao) throws SQLException {
        courseDao.addCourse(title,description);
    }

    void editCourse(int id, String title,String desc, boolean activity, CourseDao courseDao) throws SQLException {
        courseDao.editCourse(id,title,desc,activity);
    }

    Course getCourse(int courseId, CourseDao courseDao) throws SQLException {
        return courseDao.findCourseById(courseId);
    }

    List<Course> availableCourses(CourseDao courseDao) {
        return null;
    }

    void removeCourse(int id, CourseDao courseDao) throws SQLException {
        courseDao.removeCourse(id);
    }
    
    void editAssignment(int id, String title,String desc,int newMaxPt, CourseDao courseDao) throws SQLException {
        courseDao.editAssignment(id,title,desc,newMaxPt);
    }

    void addNewAssignment(String title, String description, int maxPoints, CourseDao courseDao) throws SQLException {
        courseDao.addAssignment(title,description,maxPoints);
    }

    void removeAssignment(int id, CourseDao courseDao) throws SQLException {
        courseDao.removeAssignment(id);
    }

    Assignment getAssignment(int assignmentid, CourseDao courseDao) throws SQLException {
        return courseDao.findAssignmentById(assignmentid);
    }

    List<Assignment> availableAssignments(CourseDao courseDao) throws SQLException {
        return courseDao.findAllAssignment();
    }
}

