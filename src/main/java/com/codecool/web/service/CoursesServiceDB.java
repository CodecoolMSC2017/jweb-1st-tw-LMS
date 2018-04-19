package com.codecool.web.service;

import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesServiceDB {


    public List<Course> getCourses(CourseDao courseDao) throws SQLException {
        return courseDao.findAllCourse();
    }

    public void addNewCourse(String title, String description, CourseDao courseDao) throws SQLException {
        courseDao.addCourse(title,description);
    }

    public void editCourse(int id, String title,String desc, boolean activity, CourseDao courseDao) throws SQLException {
        courseDao.editCourse(id,title,desc,activity);
    }

    public Course getCourse(int courseId, CourseDao courseDao) throws SQLException {
        return courseDao.findCourseById(courseId);
    }

    public List<Course> availableCourses(CourseDao courseDao) {
        return null;
    }

    public void removeCourse(int id, CourseDao courseDao) throws SQLException {
        courseDao.removeCourse(id);
    }
    
    public void editAssignment(int id, String title,String desc,int newMaxPt, CourseDao courseDao) throws SQLException {
        courseDao.editAssignment(id,title,desc,newMaxPt);
    }

    public void addNewAssignment(String title, String description, int maxPoints, CourseDao courseDao) throws SQLException {
        courseDao.addAssignment(title,description,maxPoints);
    }

    public void removeAssignment(int id, CourseDao courseDao) throws SQLException {
        courseDao.removeAssignment(id);
    }

    public Assignment getAssignment(int assignmentid, CourseDao courseDao) throws SQLException {
        return courseDao.findAssignmentById(assignmentid);
    }

    public List<Assignment> availableAssignments(CourseDao courseDao) throws SQLException {
        return courseDao.findAllAssignment();
    }

    public List<Course> availableCourses(List<Course> courses) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getIsActive()) {
                result.add(course);
            }
        }
        return result;
    }

    public void setPublicity(int id, CourseDao courseDao) throws  SQLException {
        Course course = courseDao.findCourseById(id);
        boolean isActive;
        if(course.getIsActive()) {
            isActive = false;
        } else {
            isActive = true;
        }
        courseDao.setActivity(id, isActive);
    }
}

