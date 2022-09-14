package com.ja.spring.data.jpa.service;

import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends ICRUD<Student, Long>{

    Page<StudentDTO> listPageable(Pageable pageable);
}
