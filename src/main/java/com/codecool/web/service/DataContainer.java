package com.codecool.web.service;

import com.codecool.web.model.Courses;
import com.codecool.web.model.Mentor;
import com.codecool.web.model.Student;
import com.codecool.web.model.Task;

import java.util.List;

public class DataContainer {
    private List<Student> students;
    private List<Mentor> mentors;
    private List<Courses> coursesList;

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        students.add(student);
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void addMentors(Mentor mentor) {
       mentors.add(mentor);
    }

    public List<Courses> getCourses() {
        return coursesList;
    }


    public void addCourse(Courses courses) {
        coursesList.add(courses);
    }
}
