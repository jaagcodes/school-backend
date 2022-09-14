package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.entity.CourseMaterial;
import com.ja.spring.data.jpa.repository.ICourseMaterialRepository;
import com.ja.spring.data.jpa.repository.ICourseRepository;
import com.ja.spring.data.jpa.repository.IGenericRepo;
import com.ja.spring.data.jpa.service.ICourseMaterialService;
import com.ja.spring.data.jpa.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialServiceImpl extends CRUDImpl<CourseMaterial, Long> implements ICourseMaterialService{

    @Autowired
    private ICourseMaterialRepository repository;

    @Override
    public IGenericRepo<CourseMaterial, Long> getRepo(){
        return repository;
    }
}
