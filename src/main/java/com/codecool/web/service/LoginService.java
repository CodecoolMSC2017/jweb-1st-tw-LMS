package com.codecool.web.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    boolean checkParams(HttpServletRequest req);
}
