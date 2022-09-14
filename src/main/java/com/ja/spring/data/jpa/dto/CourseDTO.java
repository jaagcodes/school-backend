package com.ja.spring.data.jpa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long courseId;

    @NotNull
    private String title;

    @NotNull
    private Integer credit;

    private CourseMaterialDTO courseMaterial;

    @NotNull
    private TeacherDTO teacher;

}
