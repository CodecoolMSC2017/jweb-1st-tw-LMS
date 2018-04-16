package com.codecool.web.service;

import com.codecool.web.model.Assignment;
import com.codecool.web.model.Course;
import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradingServiceImpl implements GradingService {

    //Hashmap key==>Student's ID, value==>List of his assignments.
    private Map<Integer, List<Assignment>> sentAssignments;

    public GradingServiceImpl() {
        this.sentAssignments = DataContainer.getInstance().getSentAssignments();
    }

    // TODO clean up all sysoutprints
    public void addNewAssignment(int studentId, Assignment assignment) {
        if(sentAssignments.size()> 0) {
            for (int id : sentAssignments.keySet()) {
                if (id == studentId) {
                    List<Assignment> usersAssignments = sentAssignments.get(id);
                    usersAssignments.add(assignment);
                    sentAssignments.put(id, usersAssignments);
                    System.out.println("\nNew key added: "+ studentId+"\n");
                    System.out.println("Updated student id :" + id + "List size: " + sentAssignments.get(id).size());
                } else {
                    List<Assignment> usersAssignments = new ArrayList<>();
                    usersAssignments.add(assignment);
                    sentAssignments.put(studentId, usersAssignments);
                    System.out.println("\nNew key added: "+ studentId+"\n");
                    System.out.println("New student id :" + studentId + "List size: " + sentAssignments.get(studentId).size());
                }
            }
        } else {
            List<Assignment> usersAssignments = new ArrayList<>();
            usersAssignments.add(assignment);
            sentAssignments.put(studentId, usersAssignments);
            System.out.println("New assignment id :" + studentId + "List size: " + sentAssignments.get(studentId).size());
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

    public boolean isSubmitted(int studentId, Course openedCourse) {
        List<Assignment> sentByStudent = getStudentsAssignments(studentId);
        if(sentByStudent != null) {
            for(Assignment assignment : sentByStudent) {
                if(assignment.getId() == openedCourse.getId()) {
                    return true;
                }

            }
        }
        return false;
    }
}

