package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class LoginServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        DataContainer dataCont = (DataContainer) scx.getAttribute("dataContainer");
        if (!req.getParameter("account").equals("") && !req.getParameter("pass").equals("")) {
            req.getRequestDispatcher("messages.jsp").forward(req, resp);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
