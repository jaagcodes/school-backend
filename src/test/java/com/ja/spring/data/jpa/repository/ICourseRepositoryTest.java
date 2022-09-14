package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ICourseRepositoryTest {

    @Autowired
    private ICourseRepository repository;

    @Autowired
    private ICourseMaterialRepository repositoryCourseMaterial;

    @Autowired
    private ITeacherRepository teacherRepository;

    @Autowired
    private IStudentRepository studentRepository;
    
    @Test
    public void getCourses(){

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        repositoryCourseMaterial.save(courseMaterial);

        Course course2 = Course.builder()
                .title("OOP")
                .credit(6)
                .build();
        
        repository.save(course);
        repository.save(course2);
        List<Course> courses = repository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacherObject(){

        Teacher teacher = Teacher.builder()
                .firstName("Jirafales")
                .lastName("Ruiz")
                // .courses(List.of(course, course2))
                .build();

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
        System.out.println("Curso "+repository.findAll());
    }

    @Test
    public void findALlPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(0, 2);

        Teacher teacher = Teacher.builder()
                .firstName("Jirafales")
                .lastName("Ruiz")
                // .courses(List.of(course, course2))
                .build();

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);

        Long totalELements = repository.findAll(firstPageWithThreeRecords)
                .getTotalElements();
        System.out.println("totalELements = " + totalELements);
        
        int totalPages = repository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("totalPages = " + totalPages);
        
        List<Course> courses = repository.findAll(firstPageWithThreeRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting(){
        Teacher teacher = Teacher.builder()
                .firstName("Jirafales")
                .lastName("Ruiz")
                // .courses(List.of(course, course2))
                .build();
       teacherRepository.save(teacher);

        Course course = Course.builder()
                .title("DSA")
                .credit(5)
                .teacher(teacher)
                .build();

        Course course2 = Course.builder()
                .title("OOP")
                .credit(4)
                .teacher(teacher)
                .build();

        Course course3 = Course.builder()
                .title("BackEnd-Dev")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
        repository.save(course2);
        repository.save(course3);

        Pageable sortByTitle =
                PageRequest.of(1, 2, Sort.by("title"));

        Pageable sortByCreditsDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleDesc =
                PageRequest.of(0, 2, Sort.by("title").descending());

        List<Course> courses =
                repository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTtitleContaining(){
        Teacher teacher = Teacher.builder()
                .firstName("Jirafales")
                .lastName("Ruiz")
                // .courses(List.of(course, course2))
                .build();
        teacherRepository.save(teacher);

        Course course = Course.builder()
                .title("DSA")
                .credit(5)
                .teacher(teacher)
                .build();

        Course course2 = Course.builder()
                .title("OOP")
                .credit(4)
                .teacher(teacher)
                .build();

        Course course3 = Course.builder()
                .title("BackEnd-Dev")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
        repository.save(course2);
        repository.save(course3);

        Pageable firstPageTenRecords = PageRequest.of(0, 3);

        List<Course> courses = repository.findByTitleContaining("D", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Jirafales")
                .lastName("Ruiz")
                // .courses(List.of(course, course2))
                .build();
//        teacherRepository.save(teacher);

        Guardian guardian = Guardian.builder()
                .email("ana@gmail.com")
                .mobile("56768790")
                .name("Ana")
                .build();

        Student student = Student.builder()
                .emailId("juan@gmail.com")
                .firstName("Juan")
                .lastName("Reyes")
                .guardian(guardian)
                .build();
  //      studentRepository.save(student);

        Guardian guardian2 = Guardian.builder()
                .email("andres@gmail.com")
                .mobile("45787590")
                .name("Andres")
                .build();

        Student student2 = Student.builder()
                .emailId("luisa@gmail.com")
                .firstName("Luisa")
                .lastName("Perez")
                .guardian(guardian2)
                .build();
  //      studentRepository.save(student);

        Course course = Course
                .builder()
                .title("AI")
                .credit(6)
                .teacher(teacher)
                .build();


        repository.save(course);
    }
}