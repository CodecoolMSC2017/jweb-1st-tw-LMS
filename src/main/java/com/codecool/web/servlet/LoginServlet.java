package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("account") !=null && req.getParameter("pass") !=null &&
                !req.getParameter("account").equals("") && !req.getParameter("pass").equals("");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        UserServiceImpl userServiceImpl = (UserServiceImpl)scx.getAttribute("userServiceImpl");
        if (checkParams(req)) {
            if(userServiceImpl.authenticateUser(req.getParameter("account"),req.getParameter("pass"))){
                User user = userServiceImpl.getUser(req.getParameter("account"));
                Cookie ck = new Cookie("uname",user.getName());
                resp.addCookie(ck);
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("message", "error occured");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
