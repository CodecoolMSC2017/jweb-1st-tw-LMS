package com.codecool.web.servlet;


import com.codecool.web.dao.CourseDao;
import com.codecool.web.dao.database.DatabaseCourseDao;
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


@WebServlet("/newassignment")
public class NewAssignmentServlet extends AbstractServlet {
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("title") !=null && req.getParameter("description") !=null &&
                !req.getParameter("description").equals("") && !req.getParameter("title").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();

        try(Connection connection = getConnection(scx)){
            CourseDao courseDao = new DatabaseCourseDao(connection);
            CoursesServiceDB courseService = new CoursesServiceDB();
            if (checkParams(req)) {
                int max = Integer.parseInt(req.getParameter("max"));
                courseService.addNewAssignment(req.getParameter("title"), req.getParameter("description"), max, courseDao);

                //req.getRequestDispatcher("courses.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("courses");
        }


    }
}