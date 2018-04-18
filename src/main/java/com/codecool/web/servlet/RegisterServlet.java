package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.exceptions.EmptyFieldException;
import com.codecool.web.service.RegisterServiceImpl;
import com.codecool.web.service.UserService;
import com.codecool.web.service.UserServiceDB;
import com.codecool.web.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext scx = request.getServletContext();
        String result = null;

        try (Connection connection = getConnection(scx)) {
            UserServiceDB userDb = new UserServiceDB();
            UserDao userDao = new DatabaseUserDao(connection);
            RegisterServiceImpl regService = new RegisterServiceImpl();
            regService.checkParams(request);
            userDb.register(request.getParameter("name"), request.getParameter("mail"), request.getParameter("password"), regService.isMentor(request), userDao);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AlreadyExistsException e) {
            result = e.getMessage();
            request.setAttribute("result", result);
        } catch (EmptyFieldException e) {
            result = e.getMessage();
            request.setAttribute("result", result);
        } catch (NotValidEmailException e) {
            result = e.getMessage();
            request.setAttribute("result", result);
        } finally {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}