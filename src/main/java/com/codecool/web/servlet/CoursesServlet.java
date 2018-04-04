package com.codecool.web.servlet;

import com.codecool.web.model.Course;
import com.codecool.web.model.User;
import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.DataContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        CourseServiceImpl courseServiceImpl = (CourseServiceImpl) scx.getAttribute("courseServiceImpl");
        List<Course> courses = courseServiceImpl.getCourses();
        if (courses.size() == 0) courseServiceImpl.addNewCourse("Copypaste skillz", "Tips and tricks to stackoverflow");
        if (courseServiceImpl.getTasks(courses.get(0).getId()).size() == 0) {
            courseServiceImpl.addTask(courses.get(0).getId(), "Script kiddie life", "Are you ready to hardcode?", "task");
        }

        List<Course> courseList;
        boolean permission = false;

        if (permission) {
            courseList = courses;
        } else {
            courseList = courseServiceImpl.availableCourses();
        }

        if (req.getQueryString() != null) {
            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
            String[] parameters = queryString.split("&");
            int courseid = 0;
            String mode = "view";
            for (String parameter : parameters) {
                String param1 = parameter.split("=")[0];
                if (param1.equals("courseid")) {
                    courseid = Integer.parseInt(parameter.split("=")[1]);
                } else if (param1.equals("mode")) {
                    mode = parameter.split("=")[1];
                }
            }
            if (mode.equals("view")) {
                req.setAttribute("tasks", courseServiceImpl.getTasks(courseid));
                req.setAttribute("course", courseServiceImpl.getCourse(courseid));
                req.getRequestDispatcher("course.jsp").forward(req, resp);
            } else if (mode.equals("new") || mode.equals("edit")) {
                req.setAttribute("mode", mode);
                req.setAttribute("course", courseServiceImpl.getCourse(courseid));
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            } else if (mode.equals("delete")) {
                courseServiceImpl.removeCourse(courseid);
                req.getRequestDispatcher("courses.jsp").forward(req, resp);
            } else if (mode.equals("publish")) {
                req.setAttribute("course", courseServiceImpl.setPublicity(courseid));
            }
        }
        req.setAttribute("courses", courseList);
        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }
}
