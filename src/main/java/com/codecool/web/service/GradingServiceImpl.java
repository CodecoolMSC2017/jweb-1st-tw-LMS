package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradingServiceImpl implements GradingService {

    //Hashmap key==>Student's ID, value==>List of his assignments.
    private Map<Integer, List<Assignment>> sentAssignments;

    public GradingServiceImpl() {
        this.sentAssignments = DataContainer.getInstance().getSentAssignments();
    }

    public void addNewAssignment(int studentId, Assignment assignment) {
        for (int id : sentAssignments.keySet()) {
            if (id == studentId) {
                List<Assignment> usersAssignments = sentAssignments.get(id);
                usersAssignments.add(assignment);
                sentAssignments.put(id, usersAssignments);
            } else {
                List<Assignment> usersAssignments = new ArrayList<>();
                usersAssignments.add(assignment);
                sentAssignments.put(id, usersAssignments);
            }
        }
    }

    public List<Assignment> getStudentsAssignments(int studentId) {
        for (int id : sentAssignments.keySet()) {
            if (id == studentId) {
                return sentAssignments.get(id);
            }
        }
        return null;
    }

    public Assignment getOneAssignment(int studentId, int assignId) {
        List<Assignment> studentAssignments = getStudentsAssignments(studentId);
        for (Assignment assignment : studentAssignments) {
            if (assignment.getId() == assignId) {
                return assignment;
            }
        }
        return null;
    }
}

