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

@WebServlet(name = "courses", urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        List<Course> courses = DataContainer.getInstance().getCoursesList();
        User actualUser = (User) req.getSession().getAttribute("user");
        List<Course> courseList = courseServiceImpl.getCoursesBypermission(actualUser.getPermission());
        if (req.getQueryString() != null) {
            String mode = courseServiceImpl.getModeFromURL(req);
            int courseid = courseServiceImpl.courseIdFromURL(req);

            if (mode.equals("view")) {
                req.setAttribute("course", courseServiceImpl.getCourse(courseid));
                req.getRequestDispatcher("course.jsp").forward(req, resp);
            } else if (mode.equals("new") || mode.equals("edit")) {
                req.setAttribute("mode", mode);
                req.setAttribute("course", courseServiceImpl.getCourse(courseid));
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            } else if (mode.equals("delete")) {
                courseServiceImpl.removeCourse(courseid);
            } else if (mode.equals("publish")) {
                req.setAttribute("course", courseServiceImpl.setPublicity(courseid));
            }
        }
        req.setAttribute("courses",courseList);
        req.getRequestDispatcher("courses.jsp").forward(req, resp);

    }


}
