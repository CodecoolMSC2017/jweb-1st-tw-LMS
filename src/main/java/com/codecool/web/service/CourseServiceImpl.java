package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private List<Course> courses;

    public CourseServiceImpl() {
        courses = DataContainer.getInstance().getCoursesList();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addNewCourse(String title, String description) {
        Course course = new Course(title, description);
        courses.add(course);
    }

    public void addNewAssignment(String title, String description, int maxPoints) {
        Course assignment = new Assignment(title, description, maxPoints);
        courses.add(assignment);
    }

    public void editCourse(int id, String title, String desc, boolean activity) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                Course edited = new Course(id, title, desc, activity);
                courses.set(i, edited);
            }
        }
    }

    public void editAssignment(int id, String title, String desc, int newMaxPt, boolean activity, String submission) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                Course edited = new Assignment(id, title, desc, newMaxPt, activity, id, submission);
                courses.set(i, edited);
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
            if (course.getIsActive()) {
                result.add(course);
            }
        }
        return result;
    }

    public void removeCourse(int id) {
        Iterator<Course> iter = courses.iterator();

        while (iter.hasNext()) {
            Course elem = iter.next();
            if (elem.getId() == id)
                iter.remove();
        }
    }


    public Course setPublicity(int courseId) {
        getCourse(courseId).setActivity();
        return null;
    }

    public List<Course> getCoursesBypermission(boolean permission) {
        List<Course> courseList;
        if (permission) {
            courseList = courses;
        } else {
            courseList = availableCourses();
        }
        return courseList;
    }

    public int courseIdFromURL(HttpServletRequest req) throws UnsupportedEncodingException {
        String mode = null;
        int courseId = 0;
        if (req.getQueryString() != null) {
            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
            String[] parameters = queryString.split("&");
            mode = "view";
            for (String parameter : parameters) {
                String param1 = parameter.split("=")[0];
                if (param1.equals("courseid")) {
                    courseId = Integer.parseInt(parameter.split("=")[1]);
                }
            }
        }
        return courseId;
    }

    public String getModeFromURL(HttpServletRequest req) throws UnsupportedEncodingException {
        String mode = null;

        if (req.getQueryString() != null) {
            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
            String[] parameters = queryString.split("&");
            mode = "view";
            for (String parameter : parameters) {
                String param1 = parameter.split("=")[0];
                if (param1.equals("mode")) {
                    mode = parameter.split("=")[1];
                }
            }
        }
        return mode;
    }

}
