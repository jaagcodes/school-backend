package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Guardian;
import com.ja.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IStudentRepositoryTest {

    @Autowired
    private IStudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("juan@gmail.com")
                .firstName("Juan")
                .lastName("Reyes")
                //.guardianEmail("ana@gmail.com")
                //.guardianMobile("56768790")
                //.guardianName("Ana")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

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
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){

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
        studentRepository.save(student);
        List<Student> students = studentRepository.findByFirstName("Juan");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){

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
        studentRepository.save(student);
        List<Student> students = studentRepository.findByFirstNameContaining("j");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByGuardianName(){

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
        studentRepository.save(student);
        List<Student> students = studentRepository.findByGuardianName("Ana");
        System.out.println("students = " + students);
    }

    @Test//JPQL
    public void printGetStudentByEmailAddress(){

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
        studentRepository.save(student);

        Student foundstudent = studentRepository.getStudentByEmailAddress("juan@gmail.com");
        System.out.println("student = " + foundstudent);
    }

    @Test//JPQL
    public void printGetStudentNameByEmailAddress(){

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
        studentRepository.save(student);

        String studentName = studentRepository.getStudentFirstNameByEmailAddress("juan@gmail.com");
        System.out.println("student = " + studentName);
    }

    @Test//Native
    public void printGetStudentByEmailAddressNative(){

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
        studentRepository.save(student);

        Student foundstudent = studentRepository.getStudentByEmailAddressNative("juan@gmail.com");
        System.out.println("student = " + foundstudent);
    }

    @Test//Native Named @Param
    public void printGetStudentByEmailAddressNamedParam(){

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
        studentRepository.save(student);

        Student foundstudent = studentRepository.getStudentByEmailAddressNamedParams("juan@gmail.com");
        System.out.println("student = " + foundstudent);
    }

    @Test//@Modifying and @Transactional
    public void printUpdateStudentNameByEmail(){

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
        studentRepository.save(student);

        studentRepository.updateStudentNameByEmailId("Juan Alberto", "juan@gmail.com");

        Student foundstudent = studentRepository.getStudentByEmailAddress("juan@gmail.com");
        System.out.println("student = " + foundstudent);
    }
}