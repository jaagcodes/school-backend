package com.ja.spring.data.jpa.service;

import com.ja.spring.data.jpa.dto.CourseDTO;
import com.ja.spring.data.jpa.dto.CourseMaterialDTO;
import com.ja.spring.data.jpa.dto.CourseStudentsLstDTO;
import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.CourseMaterial;
import com.ja.spring.data.jpa.entity.CourseStudent;
import com.ja.spring.data.jpa.entity.Student;

import java.util.List;
import java.util.Set;

public interface ICourseService extends ICRUD<Course, Long>{

    public CourseDTO registerCourse(CourseDTO courseDTO) throws Exception;

    public Set<StudentDTO> addStudent(CourseStudentsLstDTO courseStudentsLstDTO)throws Exception;

    public Set<StudentDTO> listStudentsByCourseId(Long courseId )throws Exception;

    public Set<CourseDTO> listCoursesByStudentId(Long studentId)throws Exception;

    public void addCourseMaterialToCourse(CourseMaterial material, Long courseId)throws Exception;
}
