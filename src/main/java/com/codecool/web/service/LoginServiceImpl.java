package com.codecool.web.service;

import javax.servlet.http.HttpServletRequest;

public class LoginServiceImpl implements LoginService{

    public boolean checkParams(HttpServletRequest req) {
        return req.getParameter("account") !=null && req.getParameter("pass") !=null &&
                !req.getParameter("account").equals("") && !req.getParameter("pass").equals("");
    }
}
