package com.codecool.web.servlet;

import com.codecool.web.model.Course;
import com.codecool.web.service.CourseServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/saveassignment")
public class SaveAssignmentServlet extends HttpServlet{
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("title") !=null && req.getParameter("description") !=null &&
                !req.getParameter("description").equals("") && !req.getParameter("title").equals("");
    }
    // TODO in 26th row makes NullPointerException
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        CourseServiceImpl courseServiceImpl = (CourseServiceImpl)scx.getAttribute("courseServiceImpl");
        if (checkParams(req)) {
            int id = (Integer)req.getSession().getAttribute("assignid");
            boolean activity = (Boolean) req.getSession().getAttribute("act");
            String submission = (String) req.getSession().getAttribute("submission");
            courseServiceImpl.editAssignment(id,req.getParameter("title"), req.getParameter("description"),activity, submission);
            resp.sendRedirect("courses");
            //req.getRequestDispatcher("courses.jsp").forward(req, resp);
        }
    }
}

