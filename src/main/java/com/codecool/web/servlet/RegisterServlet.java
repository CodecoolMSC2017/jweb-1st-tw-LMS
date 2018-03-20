package com.codecool.web.servlet;

import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext scx = request.getServletContext();
        UserService userService = (UserService)scx.getAttribute("userService");
        String username = request.getParameter("name");
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        String result;
        if(userService.register(username, email, password)) {
            result = "Successfully registered";
        } else {
            result = "Something went wrong try again";
        }
        request.setAttribute("result", result);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}