package com.ja.spring.data.jpa.controller;

import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Student;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/listStudent/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Long id) throws Exception{
        Student student = studentService.findById(id).orElseThrow(()-> new ModelNotFoundException("Model with id: "+id+" not found"));
        StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
        return new ResponseEntity<>(studentDTO, HttpStatus.FOUND);
    }

    //localhost:8080/students/pageable?page=0&size=10
    @GetMapping("/pageable")
    public ResponseEntity<Page<StudentDTO>> listPageable(Pageable pageable) throws Exception{
        Page<StudentDTO> students = studentService.listPageable(pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/findAllStudents")
    public ResponseEntity<List<StudentDTO>> findAll()throws Exception{
        List<StudentDTO> studentsDTO= studentService.listAll()
                                                    .stream()
                                                    .map( s -> mapper.map(s, StudentDTO.class))
                                                    .collect(Collectors.toList());
        return new ResponseEntity<>(studentsDTO, HttpStatus.FOUND);
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<Student> register(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student student = studentService.register(mapper.map(studentDTO, Student.class));

        return new ResponseEntity(student, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO)throws Exception{

        Student student = studentService.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new ModelNotFoundException("Model with id: "+studentDTO.getStudentId()+" not found"));

        Student studentUpdated  = studentService.update(mapper.map(studentDTO, Student.class));
        StudentDTO studentDTOUpdated = mapper.map(studentUpdated, StudentDTO.class);
        return new ResponseEntity<>(studentDTOUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long studentId) throws Exception{
        Student student = studentService.findById(studentId).orElseThrow(() -> new ModelNotFoundException("Model with id: "+studentId+" not found"));

        studentService.deleteEntity(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
