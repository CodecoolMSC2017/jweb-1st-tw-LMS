package com.codecool.web.servlet;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.User;
import com.codecool.web.service.DataContainer;
import com.codecool.web.service.GradingServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/collectassignment")
public class CollectAssignmentsServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = req.getServletContext();
        GradingServiceImpl gradingService = new GradingServiceImpl();
        User actualUser = (User) req.getSession().getAttribute("user");
        Assignment sentAssignment = (Assignment) req.getSession().getAttribute("submittedAssign");
        gradingService.addNewAssignment(actualUser.getId(),sentAssignment);
        Map<Integer,List<Assignment>> result = DataContainer.getInstance().getSentAssignments();
        System.out.println("Map size is: " + result.size());
        resp.sendRedirect("courses");
    }

}
