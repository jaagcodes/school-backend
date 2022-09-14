package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Course;
import com.ja.spring.data.jpa.entity.CourseStudent;
import com.ja.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface ICourseStudentRepository extends IGenericRepo<CourseStudent, Long>{

    @Modifying
    @Transactional
    @Query(value="INSERT INTO course_student(id_course, id_student) VALUES(:idCourse, :idStudent)", nativeQuery = true )
    Integer register(@Param("idCourse")Long idCourse, @Param("idStudent") Long idStudent);

    @Query(value = "FROM CourseStudent cs WHERE cs.course.courseId = :courseId")
    Set<CourseStudent> listCourseStudentsByCourseId(@Param("courseId")Long courseId);

    @Query(value="DELETE FROM course_student WHERE id_course = :idCourse", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByCourseId(@Param("idCourse")Long idCourse);

    @Query(value = "DELETE FROM CourseStudent cs WHERE cs.student.studentId = :idStudent")
    @Modifying
    @Transactional
    void deleteByStudentId(@Param("idStudent") Long idStudent);

    @Query(value = "SELECT s FROM CourseStudent cs " +
                "INNER JOIN Student s " +
                "ON cs.student.studentId = s.studentId " +
                "WHERE cs.course.courseId = :courseId")
    Set<Student> listStudentsByCourseId(@Param("courseId")Long courseId);

    @Query(value = "SELECT c FROM CourseStudent cs " +
                   "INNER JOIN Course c " +
                   "ON cs.course.courseId = c.courseId " +
                   "WHERE cs.student.studentId = :studentId ")
    Set<Course> listCoursesByStudentId(@Param("studentId") Long studentId);

}
