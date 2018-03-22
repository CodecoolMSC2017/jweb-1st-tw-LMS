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
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uname")) {
                    ServletContext scx = req.getServletContext();
                    UserServiceImpl userServiceImpl = new UserServiceImpl();
                    List<User> users = userServiceImpl.getUsers();
                    if (users.size() == 0) userServiceImpl.addNewUser(new User(1, "admin", "admin@admin.com", "admin", true));
                    if (req.getQueryString() != null) {
                        String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
                        String[] parameters = queryString.split("&");
                        for (String parameter : parameters) {
                            String param1 = parameter.split("=")[0];
                            int param2 = Integer.parseInt(parameter.split("=")[1]);
                            if (param1.equals("userid")) {
                                User user = userServiceImpl.getUserById(param2);
                                req.setAttribute("user", user);
                                req.getRequestDispatcher("student.jsp").forward(req, resp);
                            }
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
    }
}