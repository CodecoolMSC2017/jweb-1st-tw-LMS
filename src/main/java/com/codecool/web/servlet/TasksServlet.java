package com.codecool.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uname")) {
                    if (req.getQueryString() != null) {
                        String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
                        String[] parameters = queryString.split("&");
                        int taskid = 0;
                        for (String parameter : parameters) {
                            if (parameter.split("=")[0].equals("taskid")) {
                                taskid = Integer.parseInt(parameter.split("=")[1]);
                                req.setAttribute("taskid", taskid);
                                req.getRequestDispatcher("task.jsp").forward(req, resp);
                            }
                        }
                    }
                    req.getRequestDispatcher("tasks.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("page", "tasks");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}