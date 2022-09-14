package com.ja.spring.data.jpa.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private LocalDateTime dateTime;
    private String message;
    private String details;
}
