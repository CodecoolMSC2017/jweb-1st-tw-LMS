package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataContainer;
import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        UserService userService = (UserService)scx.getAttribute("userService");
        if (!req.getParameter("account").equals("") && !req.getParameter("pass").equals("")) {
            if(userService.authenticateUser(req.getParameter("account"),req.getParameter("pass"))){
                User user = userService.getUser(req.getParameter("account"));
                resp.sendRedirect("home");
            }
        }
        resp.sendRedirect("/login");

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
