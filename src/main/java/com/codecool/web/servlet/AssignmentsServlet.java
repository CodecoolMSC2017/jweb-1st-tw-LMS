package com.codecool.web.servlet;

import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.database.DatabaseCourseDao;
import com.codecool.web.model.Course;
import com.codecool.web.model.User;
import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.CoursesServiceDB;
import com.codecool.web.service.GradingServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/assignment")
public class AssignmentsServlet extends AbstractServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        try (Connection connection = getConnection(scx)) {
            CourseDao courseDao = new DatabaseCourseDao(connection);
            CoursesServiceDB coursesServiceDB = new CoursesServiceDB();
            List<Course> courses = coursesServiceDB.getCourses(courseDao);
            List<Course> courseList;
            User actualUser = (User) req.getSession().getAttribute("user");
            boolean permission = actualUser.getPermission();

            if (permission) {
                courseList = courses;
            } else {
                courseList = coursesServiceDB.availableCourses(courses);
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
                    req.setAttribute("course", coursesServiceDB.getAssignment(courseid, courseDao));
                    req.getRequestDispatcher("course.jsp").forward(req, resp);
                } else if (mode.equals("new") || mode.equals("edit")) {
                    req.setAttribute("mode", mode);
                    req.getRequestDispatcher("editassign.jsp").forward(req, resp);
                } else if (mode.equals("delete")) {
                    coursesServiceDB.removeCourse(courseid, courseDao);
                } else if (mode.equals("publish")) {
                    coursesServiceDB.setPublicity(courseid, courseDao);
                }
            }
            req.setAttribute("courses", courseList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.getRequestDispatcher("courses.jsp").forward(req, resp);
        }
    }
}
