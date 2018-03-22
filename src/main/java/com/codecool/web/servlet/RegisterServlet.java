package com.codecool.web.servlet;

import com.codecool.web.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public boolean checkParams(HttpServletRequest req) {
        System.out.println("regservlet: name: " + req.getParameter("name") + " email: " + req.getParameter("mail") + " pw: " + req.getParameter("password"));
        return req.getParameter("name") !=null && req.getParameter("password") !=null &&
                !req.getParameter("name").equals("") && !req.getParameter("password").equals("") &&
                req.getParameter("mail") != null && !req.getParameter("mail").equals("");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext scx = request.getServletContext();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        String result;
        if (checkParams(request)) {
            String username = request.getParameter("name");
            String email = request.getParameter("mail");
            String password = request.getParameter("password");
            result = userServiceImpl.register(username, email, password);
            request.setAttribute("result", result);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            result = "please fill every fields";
        }
        request.setAttribute("result", result);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}