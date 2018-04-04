package com.codecool.web.servlet;

import com.codecool.web.model.Course;
import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user")
public class SaveUserServlet extends HttpServlet{
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("e-mail") !=null && req.getParameter("password") !=null &&
                !req.getParameter("e-mail").equals("") && !req.getParameter("password").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        UserServiceImpl userServiceImpl = (UserServiceImpl) scx.getAttribute("userServiceImpl");
        if (checkParams(req)) {
            int id = (Integer)req.getSession().getAttribute("id");
            String role = req.getParameter("role");
            boolean roletype = false;
            if (role.equals("mentor")){
                roletype = true;
            }
            userServiceImpl.editUser(id,req.getParameter("email"), req.getParameter("password"), roletype);
            resp.sendRedirect("user");
        }
    }
}
