package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.LoginServiceImpl;
import com.codecool.web.service.UserServiceImpl;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {



    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext scx = req.getServletContext();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        LoginServiceImpl loginService = new LoginServiceImpl();

        if (loginService.checkParams(req)) {
            if(userServiceImpl.authenticateUser(req.getParameter("account"),req.getParameter("pass"))){
                User user = userServiceImpl.getUser(req.getParameter("account"));
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("home");
                //req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("message", "error occured");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
