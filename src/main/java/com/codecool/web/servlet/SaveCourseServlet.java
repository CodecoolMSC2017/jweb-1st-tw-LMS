package com.codecool.web.servlet;

import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.database.DatabaseCourseDao;
import com.codecool.web.model.Course;
import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.CoursesServiceDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/savecourse")
public class SaveCourseServlet extends AbstractServlet {
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("title") != null && req.getParameter("description") != null &&
                !req.getParameter("description").equals("") && !req.getParameter("title").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        try (Connection connection = getConnection(scx)) {
            CoursesServiceDB coursesServiceDB = new CoursesServiceDB();
            CourseDao courseDao = new DatabaseCourseDao(connection);
            if (checkParams(req)) {
                Course oldCourse = (Course) req.getSession().getAttribute("course");

                coursesServiceDB.editCourse(oldCourse.getId(), req.getParameter("title"), req.getParameter("description"), oldCourse.getIsActive(), courseDao);

                resp.sendRedirect("courses");
                //req.getRequestDispatcher("courses.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

