package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;

import java.util.ArrayList;
import java.util.List;

public interface GradingService {

    void addNewAssignment(int studentId, Assignment assignment);

    List<Assignment> getStudentsAssignments(int studentId);

    Assignment getOneAssignment(int studentId, int assignId);

    boolean isSubmitted(int studentId, Course openedCourse);
}



 