package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private List<Course> courses;

    public CourseServiceImpl() {
        courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addNewCourse(String title, String description) {
        Course course = new Course(title, description);
        courses.add(course);
    }

    public void addNewAssignment(String title, String description) {
        Course assignment = new Assignment(title,description);
        courses.add(assignment);
    }

    public void editCourse(int id, String title,String desc, boolean activity) {
        for(int i = 0; i< courses.size(); i++ ) {
            if(courses.get(i).getId() == id) {
                Course edited = new Course(id, title, desc, activity);
                courses.set(i,edited);
            }
        }
    }

    public Course getCourse(int courseid) {
        for (Course c : courses) {
            if (c.getId() == courseid) {
                return c;
            }
        }
        return null;
    }

    public List<Course> availableCourses() {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getActive()) {
                result.add(course);
            }
        }
        return result;
    }
    //TODO using ITERATOR to avoid ConcurrentModificaton
    public void removeCourse(int id) {

        Iterator<Course> iter = courses.iterator();

        while (iter.hasNext()) {
            Course elem = iter.next();
            if (elem.getId()==id)
                iter.remove();
        }
    }


    public Course setPublicity(int courseId){
        getCourse(courseId).setActivity();
        return null;
    }
}
