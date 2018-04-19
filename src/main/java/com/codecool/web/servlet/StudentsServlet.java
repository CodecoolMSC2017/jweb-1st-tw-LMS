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
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class StudentsServlet extends AbstractServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actualUser = (User) req.getSession().getAttribute("user");
        boolean canEdit = false;
        ServletContext scx = req.getServletContext();
        try (Connection connection = getConnection(scx)) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserServiceDB userServiceDB = new UserServiceDB();
            List<User> listOfUsers = userServiceDB.getUsers(userDao);
            if (actualUser.getName() != null) {
                for (User userList : listOfUsers) {
                    System.out.println(userList);
                    if (userList.getName().equals(actualUser.getName())) {
                        List<User> users = userServiceDB.getUsers(userDao);
                        if (req.getQueryString() != null) {
                            String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
                            String[] parameters = queryString.split("&");
                            for (String parameter : parameters) {
                                String param1 = parameter.split("=")[0];
                                int param2 = Integer.parseInt(parameter.split("=")[1]);
                                if (param2 == actualUser.getId() || actualUser.getPermission()) {
                                    canEdit = true;
                                }
                                req.setAttribute("canEdit", canEdit);
                                if (param1.equals("userid")) {
                                    User user = userServiceDB.getUserById(param2, userDao);
                                    req.setAttribute("user", user);

                                }
                                req.getRequestDispatcher("student.jsp").forward(req, resp);
                            }
                        }
                        req.setAttribute("users", users);
                        req.getRequestDispatcher("students.jsp").forward(req, resp);
                    }
                }
            } else {
                req.setAttribute("page", "error");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
