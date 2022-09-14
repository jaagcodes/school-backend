package com.ja.spring.data.jpa.repository;

import com.ja.spring.data.jpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseMaterialRepository extends IGenericRepo<CourseMaterial, Long> {

}
