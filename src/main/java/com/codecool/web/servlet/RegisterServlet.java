package com.codecool.web.servlet;

import com.codecool.web.service.RegisterService;
import com.codecool.web.service.RegisterServiceImpl;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext scx = request.getServletContext();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        RegisterServiceImpl regService = new RegisterServiceImpl();
        String result;
        if (regService.checkParams(request)) {
            if(!userServiceImpl.authenticateUser(request.getParameter("name"),request.getParameter("password"))) {
                /*String username = ;
                String email = ;
                String password = ;*/
                result = userServiceImpl.register(request.getParameter("name"), request.getParameter("mail"), request.getParameter("password"), regService.isMentor(request));

                request.setAttribute("result", result);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else {
                result = "User " + request.getParameter("name") +" is already exists!";
            }
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