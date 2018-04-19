package com.codecool.web.service;

import com.codecool.web.exceptions.EmptyFieldException;
import com.codecool.web.exceptions.NotValidEmailException;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {

    boolean checkParams(HttpServletRequest req) throws EmptyFieldException;

    boolean isMentor(HttpServletRequest req);

    boolean checkEmail(HttpServletRequest req) throws NotValidEmailException;
}