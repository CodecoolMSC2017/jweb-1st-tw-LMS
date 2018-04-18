package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginServiceImpl;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Connection getConnection(ServletContext sce) throws SQLException {
        DataSource dataSource = (DataSource) sce.getAttribute("dataSource");
        return dataSource.getConnection();
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            LoginServiceImpl loginService = new LoginServiceImpl(userDao);

            String name = req.getParameter("account");
            String password = req.getParameter("pass");

            User user = loginService.loginUser(name, password);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            System.out.println("Invalid login");
        }
    }
}
