package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IStudentRepository extends IGenericRepo<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL (JAVA PERSISTENT QUERY LANGUAGE)
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String EmailId);

    //JPQL (JAVA PERSISTENT QUERY LANGUAGE)
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native Query
    @Query(value = "select * from tbl_student where email_address = ?1", nativeQuery = true)
    Student  getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query(
            value = "select * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student  getStudentByEmailAddressNamedParams(@Param("emailId") String emailId);

    //@Modifying- update
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
