package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet {

    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("account") !=null && req.getParameter("pass") !=null &&
                !req.getParameter("account").equals("") && !req.getParameter("pass").equals("");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie ck: cookies) {
            if (ck.getName().equals("uname")) {
                ck.setMaxAge(0);
                resp.addCookie(ck);
                break;
            }
        }
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}