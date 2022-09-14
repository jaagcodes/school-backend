package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.dto.CourseDTO;
import com.ja.spring.data.jpa.dto.CourseStudentsLstDTO;
import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.CourseMaterial;
import com.ja.spring.data.jpa.entity.Student;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.repository.*;
import com.ja.spring.data.jpa.service.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl extends CRUDImpl<Course, Long> implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ICourseMaterialService courseMaterialService;

    @Autowired
    private ICourseStudentRepository courseStudentRepository;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public IGenericRepo<Course, Long> getRepo() {
        return courseRepository;
    }


    @Override
    public CourseDTO registerCourse(CourseDTO courseDTO) throws Exception{

        Course course = mapper.map(courseDTO, Course.class);
        Long teacherId = courseDTO.getTeacher().getTeacherId();
        CourseDTO courseResponseDTO;

        teacherService.findById(teacherId)
                .orElseThrow(() -> new ModelNotFoundException("Can´t register the course because teacher with id: "+teacherId+" doesn´t exist"));

        if(course.getCourseMaterial()!=null){
            CourseMaterial courseMaterial = mapper.map(course.getCourseMaterial(), CourseMaterial.class);
            courseResponseDTO = mapper.map(registerCourseAndMaterial(course, courseMaterial), CourseDTO.class);
        }else{
            courseResponseDTO = mapper.map(super.register(course), CourseDTO.class);
        }
        return courseResponseDTO;
    }

    @Transactional
    private Course registerCourseAndMaterial(Course course, CourseMaterial material)throws Exception{
        courseMaterialService.register(material);
        course.getCourseMaterial().setCourseMaterialId(material.getCourseMaterialId());
        return courseRepository.save(course);
    }

    @Override
    public Set<StudentDTO> addStudent(CourseStudentsLstDTO courseStudentsLstDTO) throws Exception {

        Course course = mapper.map(courseStudentsLstDTO.getCourse(), Course.class);
        Set<Student> students = mapper.map(courseStudentsLstDTO.getStudentsList(), new TypeToken<Set<Student>>() {}.getType());
        Course foundedCourse = courseRepository.findById(course.getCourseId()).orElseThrow(()-> new ModelNotFoundException("Course not found"));
        Set<Student> foundedStudents = students.stream()
                                               .map(s -> {
                                                   try {
                                                       return studentService.findById(s.getStudentId()).orElseThrow(()-> new ModelNotFoundException("Student with id: "+s.getStudentId()+" not found"));
                                                   } catch (Exception e) {
                                                       throw new RuntimeException(e);
                                                   }
                                               })
                                               .collect(Collectors.toSet());

        foundedStudents.forEach(s -> {
            try {
                courseStudentRepository.register(foundedCourse.getCourseId(), s.getStudentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Set<StudentDTO> studentsDTO = foundedStudents.stream().map(s -> mapper.map(s, StudentDTO.class)).collect(Collectors.toSet());
        return studentsDTO;
    }

    @Override
    public Set<StudentDTO> listStudentsByCourseId(Long courseId )throws Exception{
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new ModelNotFoundException("Course with id: "+courseId+" not found"));
        Set<Student> students = courseStudentRepository.listStudentsByCourseId(courseId);
        Set<StudentDTO> studentsDTO = students.stream().map(s -> mapper.map(s, StudentDTO.class)).collect(Collectors.toSet());
        return studentsDTO;
    }

    @Override
    public Set<CourseDTO> listCoursesByStudentId(Long studentId)throws Exception{
        Student student = studentService.findById(studentId).orElseThrow(()-> new ModelNotFoundException("Student with id: "+studentId+" not found"));
        Set<Course> courses = courseStudentRepository.listCoursesByStudentId(studentId);
        Set<CourseDTO> coursesDTO = courses.stream().map(c -> mapper.map(c, CourseDTO.class)).collect(Collectors.toSet());
        return coursesDTO;
    }

    @Override
    @Transactional
    public void addCourseMaterialToCourse(CourseMaterial material, Long courseId)throws Exception{

        Course foundedCourse = courseRepository.findById(courseId)
                                               .orElseThrow(()-> new ModelNotFoundException("Course not Found"));

        courseMaterialService.register(material);
        foundedCourse.setCourseMaterial(material);
        courseRepository.save(foundedCourse);

    }

    @Override
    @Transactional
    public void deleteEntity(Long aLong) throws Exception {

        courseRepository.findById(aLong).orElseThrow(()-> new ModelNotFoundException("Course with id: "+aLong+" not found"));
        courseStudentRepository.deleteByCourseId(aLong);
        super.deleteEntity(aLong);
    }
}
