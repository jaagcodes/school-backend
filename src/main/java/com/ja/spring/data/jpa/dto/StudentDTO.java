package com.ja.spring.data.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long studentId;


    private String firstName;


    private String lastName;



    private String emailId;


    private GuardianDTO guardian;
}
