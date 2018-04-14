package com.codecool.web.service;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {

    boolean checkParams(HttpServletRequest req);

    boolean isMentor(HttpServletRequest req);

}