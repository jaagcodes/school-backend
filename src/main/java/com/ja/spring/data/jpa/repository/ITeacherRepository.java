package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends IGenericRepo<Teacher, Long> {

    public Teacher findByFirstName(String firstName);
}
