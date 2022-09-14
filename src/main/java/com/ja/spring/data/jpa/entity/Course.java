package com.ja.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long courseId;

    private String title;

    private Integer credit;

    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId",
            foreignKey = @ForeignKey(name = "FK_course_teacher")
    )
    private Teacher teacher;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "courseMaterial_id",
            referencedColumnName = "courseMaterialId",
            foreignKey = @ForeignKey(name = "material_course_FK")

    )
    private CourseMaterial courseMaterial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
