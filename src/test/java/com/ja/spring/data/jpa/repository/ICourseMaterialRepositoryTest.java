package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ICourseMaterialRepositoryTest {

    @Autowired
    private ICourseMaterialRepository repository;

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    public void SaveCourseMaterial(){

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
       //courseRepository.save(course);

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        repository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        //courseRepository.save(course);

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        repository.save(courseMaterial);
        
        List<CourseMaterial> materials = repository.findAll();
        System.out.println("materials = " + materials);
    }

}