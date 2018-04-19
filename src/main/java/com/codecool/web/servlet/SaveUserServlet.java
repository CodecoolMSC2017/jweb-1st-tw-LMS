package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserServiceDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/edituser")
public class SaveUserServlet extends AbstractServlet {
    private boolean checkParams(HttpServletRequest req) {
        return req.getParameter("e-mail") != null && req.getParameter("password") != null &&
            !req.getParameter("e-mail").equals("") && !req.getParameter("password").equals("");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        UserServiceDB userServiceDB = new UserServiceDB();
        ServletContext scx = req.getServletContext();
        Boolean role = actualUser.getPermission();
        boolean roleType = false;
        if (role.equals(true)){
            roleType = true;
        }
        try (Connection connection = getConnection(scx)) {
            UserDao userDao = new DatabaseUserDao(connection);
            if (actualUser.getPermission()) {
                if (checkParams(req)) {
                    int userId = Integer.parseInt(req.getParameter("id"));
                    userServiceDB.editUser(req.getParameter("e-mail"), req.getParameter("password"), roleType, userId, userDao);
                    resp.sendRedirect("users");
                }
            } else {
                if (checkParams(req)) {
                    int id = actualUser.getId();
                    userServiceDB.editUser(req.getParameter("e-mail"), req.getParameter("password"), roleType, id, userDao);
                    resp.sendRedirect("users");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        ServletContext scx = req.getServletContext();
        try (Connection connection = getConnection(scx)) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserServiceDB userServiceDB = new UserServiceDB();
            List<User> listOfUsers = userServiceDB.getUsers(userDao);
            if (actualUser.getName() != null) {
                for (User userList : listOfUsers) {
                    if (userList.getName().equals(actualUser.getName())) {
                        req.setAttribute("user", actualUser);
                        req.getRequestDispatcher("edituser.jsp").forward(req, resp);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
