package com.codecool.web.service;

import javax.servlet.http.HttpServletRequest;

public class RegisterServiceImpl implements RegisterService {

    public boolean checkParams(HttpServletRequest req) {
        System.out.println("regservlet: name: " + req.getParameter("name") + " email: " + req.getParameter("mail") + " pw: " + req.getParameter("password"));
        return req.getParameter("name") !=null && req.getParameter("password") !=null &&
                !req.getParameter("name").equals("") && !req.getParameter("password").equals("") &&
                req.getParameter("mail") != null && !req.getParameter("mail").equals("");
    }

    public boolean isMentor(HttpServletRequest req) {
        if(req.getParameter("permission").equals("mentor")){
            return true;
        }
        return false;
    }

}
