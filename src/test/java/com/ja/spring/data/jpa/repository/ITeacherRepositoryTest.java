package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ITeacherRepositoryTest {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course course = Course.builder()
                .title("OOP")
                .credit(6)
                .build();

        Course course2 = Course.builder()
                .title("DSA")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Jirafales").
                lastName("Ruiz")
               // .courses(List.of(course, course2))
                .build();
        Teacher persisTeacher1 = teacherRepository.save(teacher);
        System.out.println("teacher = " + teacherRepository.findByFirstName("Jirafales"));
    }
}