package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends IGenericRepo<Course, Long> {

    Page<Course> findByTitleContaining(String title, Pageable pageRequest);
}
