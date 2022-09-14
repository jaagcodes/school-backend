package com.ja.spring.data.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuardianDTO {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @Size(min = 10, max = 10)
    private String mobile;
}
