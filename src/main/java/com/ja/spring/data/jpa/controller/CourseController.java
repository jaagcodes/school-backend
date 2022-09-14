package com.ja.spring.data.jpa.controller;

import com.ja.spring.data.jpa.dto.CourseDTO;
import com.ja.spring.data.jpa.dto.CourseMaterialDTO;
import com.ja.spring.data.jpa.dto.CourseStudentsLstDTO;
import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.CourseMaterial;
import com.ja.spring.data.jpa.entity.Student;
import com.ja.spring.data.jpa.entity.Teacher;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.service.ICourseService;
import com.ja.spring.data.jpa.service.IStudentService;
import com.ja.spring.data.jpa.service.ITeacherService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @Autowired
    private ModelMapper mapper;

    @PostMapping("/registerCourse")
    public ResponseEntity<CourseDTO> register(@Valid @RequestBody CourseDTO courseDTO) throws Exception{

        CourseDTO courseResponse = courseService.registerCourse(courseDTO);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @GetMapping("/listAllCourses")
    public ResponseEntity<List<CourseDTO>> listAll() throws Exception{
        List<CourseDTO> courses = courseService.listAll().stream().map(c -> mapper.map(c, CourseDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/listCourse/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") Long courseId) throws  Exception{
        Course course = courseService.findById(courseId).orElseThrow(()-> new ModelNotFoundException("Course with id: "+courseId+" not found"));
        CourseDTO courseDTO = mapper.map(course, CourseDTO.class);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/listStudentsByCourse/{courseId}")
    public ResponseEntity<Set<StudentDTO>> listStudentsByCourse(@PathVariable("courseId") Long courseId)throws Exception{

        Set<StudentDTO> studentsDTO = courseService.listStudentsByCourseId(courseId);;
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK) ;

    }

    @GetMapping("/listCoursesByStudent/{studentId}")
    public ResponseEntity<Set<CourseDTO>> listCourseByStudent(@PathVariable("studentId") Long studentId) throws Exception{

        Set<CourseDTO> studentCoursesDTO = courseService.listCoursesByStudentId(studentId);
        return new ResponseEntity<>(studentCoursesDTO, HttpStatus.OK);
    }

    @PutMapping("/addStudents")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    public ResponseEntity<Set<StudentDTO>> addStudents(@Valid @RequestBody CourseStudentsLstDTO courseStudentsLstDTO) throws Exception{

        Set<StudentDTO> addedStudents = courseService.addStudent(courseStudentsLstDTO);
        return new ResponseEntity<>(addedStudents, HttpStatus.CREATED);

    }

    @PutMapping("/addCourseMaterial/{courseId}")
    public ResponseEntity<Void> addCourseMaterialToCourse(@Valid @RequestBody CourseMaterialDTO materialDTO, @PathVariable("courseId") Long courseId) throws Exception{

        Course foundedCourse = courseService.findById(courseId).orElseThrow(()-> new ModelNotFoundException("Course with id: "+courseId+" not found"));

        CourseMaterial material = mapper.map(materialDTO, CourseMaterial.class);
        courseService.addCourseMaterialToCourse(material, courseId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long courseId)throws Exception{
        Course course = courseService.findById(courseId).orElseThrow(()->new ModelNotFoundException("Course with id: "+courseId+" not found"));
        courseService.deleteEntity(course.getCourseId());
        return ResponseEntity.noContent().build();
    }

}
