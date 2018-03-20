package com.codecool.web.servlet;

import com.codecool.web.model.Course;
import com.codecool.web.service.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        CourseService courseService = (CourseService)scx.getAttribute("courseService");
        List<Course> courses = courseService.getCourses();
        if (req.getQueryString() != null) {
            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
            String[] parameters = queryString.split("&");
            int courseid = 0;
            for (String parameter : parameters) {
                String param1 = parameter.split("=")[0];
                int param2 = Integer.parseInt(parameter.split("=")[1]);
                if (param1.equals("courseid")) {
                    courseid = param2;
                    req.setAttribute("courseid", courseid);
                    req.getRequestDispatcher("course.jsp").forward(req, resp);
                }
            }
        }
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("courses.jsp").forward(req, resp);
    }
}
