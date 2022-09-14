package com.ja.spring.data.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {


    private Long teacherId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
