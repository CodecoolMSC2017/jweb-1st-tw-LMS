package com.codecool.web.servlet;

import com.codecool.web.model.Course;
import com.codecool.web.model.User;
import com.codecool.web.service.CourseServiceImpl;
import com.codecool.web.service.DataContainer;
import com.codecool.web.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;


@WebServlet("/edituser")
public class SaveUserServlet extends HttpServlet{
    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("e-mail") !=null && req.getParameter("password") !=null &&
                !req.getParameter("e-mail").equals("") && !req.getParameter("password").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        if (actualUser.getPermission()) {
            if (checkParams(req)) {
                int id = actualUser.getId();
                String role = req.getParameter("role");
                boolean roletype = false;
                if (role.equals("mentor")) {
                    roletype = true;
                }
                userServiceImpl.editUser(id, req.getParameter("e-mail"), req.getParameter("password"), roletype);
                resp.sendRedirect("users");
            }
        } else {
            if (checkParams(req)) {
                int id = actualUser.getId();
                userServiceImpl.editUser(id, req.getParameter("e-mail"), req.getParameter("password"), false);
                resp.sendRedirect("users");
            }
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        List<User> userses = DataContainer.getInstance().getUsersList();
        if (actualUser.getName() != null) {
            for (User userList : userses) {
                if (userList.getName().equals(actualUser.getName())) {
                    req.setAttribute("user", actualUser);
                    req.getRequestDispatcher("edituser.jsp").forward(req, resp);
                }
            }
        }
    }
}
