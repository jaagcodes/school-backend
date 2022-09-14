package com.ja.spring.data.jpa.dto;

import com.ja.spring.data.jpa.entity.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class CourseStudentsLstDTO {

    @NotNull
    private CourseDTO course;

    @NotNull
    private Set<StudentDTO> studentsList;

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public Set<StudentDTO> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(Set<StudentDTO> studentsList) {
        this.studentsList = studentsList;
    }
}
