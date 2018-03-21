package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataContainer;
import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("account") !=null && req.getParameter("pass") !=null &&
                !req.getParameter("account").equals("") && !req.getParameter("pass").equals("");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        UserService userService = (UserService)scx.getAttribute("userService");
        if (checkParams(req)) {
            if(userService.authenticateUser(req.getParameter("account"),req.getParameter("pass"))){
                User user = userService.getUser(req.getParameter("account"));
                Cookie ck = new Cookie("uname",user.getName());
                resp.addCookie(ck);
                req.getRequestDispatcher("home.jsp").forward(req,resp);
            }
        }
        req.setAttribute("message", "error occured");
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
