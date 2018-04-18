package com.codecool.web.servlet;

import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.exceptions.EmptyFieldException;
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
        String result = null;

            try {
                regService.checkParams(request);
                userServiceImpl.register(request.getParameter("name"), request.getParameter("mail"), request.getParameter("password"), regService.isMentor(request));
            } catch (NotValidEmailException e) {
                result = e.getMessage();
            } catch (AlreadyExistsException e) {
                result = e.getMessage();
                request.setAttribute("result", result);
            } catch (EmptyFieldException e) {
                result = e.getMessage();
            } finally {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}