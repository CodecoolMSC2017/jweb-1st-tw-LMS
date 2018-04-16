package com.codecool.web.servlet;


import com.codecool.web.service.CourseServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/newassignment")
public class NewAssignmentServlet extends HttpServlet {
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("title") !=null && req.getParameter("description") !=null &&
                !req.getParameter("description").equals("") && !req.getParameter("title").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        if (checkParams(req)) {
            int max = Integer.parseInt(req.getParameter("max"));
            courseServiceImpl.addNewAssignment(req.getParameter("title"), req.getParameter("description"), max);
            resp.sendRedirect("courses");
            //req.getRequestDispatcher("courses.jsp").forward(req, resp);
        }
    }
}