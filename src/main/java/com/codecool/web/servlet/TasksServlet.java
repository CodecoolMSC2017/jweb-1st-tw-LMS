package com.codecool.web.servlet;

import com.codecool.web.model.Task;
import com.codecool.web.service.CourseServiceImpl;

import javax.servlet.ServletContext;
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
        ServletContext scx = req.getServletContext();
        CourseServiceImpl courseServiceImpl = (CourseServiceImpl)scx.getAttribute("courseServiceImpl");
        Cookie[] cookies = req.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uname")) {
                    if (req.getQueryString() != null) {
                        String queryString = URLDecoder.decode(req.getQueryString(), "UTF-8");
                        String[] parameters = queryString.split("&");
                        int courseid = 0;
                        int taskid = 0;
                        String mode = "view";
                        for (String parameter: parameters) {
                            String param1 = parameter.split("=")[0];
                            if (parameter.split("=")[0].equals("courseid")) {
                                courseid = Integer.parseInt(parameter.split("=")[1]);
                            } else if (parameter.split("=")[0].equals("taskid")) {
                                Task task = courseServiceImpl.getTask(courseid, Integer.parseInt(parameter.split("=")[1]));
                                req.setAttribute("task", task);
                                req.getRequestDispatcher("task.jsp").forward(req, resp);
                            } else if (param1.equals("mode")) {
                                mode = parameter.split("=")[1];
                            }
                        }
                        if (mode.equals("view")) {
                            req.getRequestDispatcher("task.jsp").forward(req, resp);
                        } else if (mode.equals("new")) {
                            req.getRequestDispatcher("edit.jsp").forward(req, resp);
                        } else if (mode.equals("edit")) {
                            req.getRequestDispatcher("edit.jsp").forward(req, resp);
                        }
                    } else {
                        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
                    }
                }
            }
        } else {
            req.setAttribute("page", "tasks");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}