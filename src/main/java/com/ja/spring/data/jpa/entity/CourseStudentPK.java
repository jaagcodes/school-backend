package com.ja.spring.data.jpa.entity;


import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseStudentPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne()
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentPK that = (CourseStudentPK) o;
        return course.equals(that.course) && student.equals(that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student);
    }
}
