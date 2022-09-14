package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.entity.Teacher;
import com.ja.spring.data.jpa.repository.IGenericRepo;
import com.ja.spring.data.jpa.repository.ITeacherRepository;
import com.ja.spring.data.jpa.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends CRUDImpl<Teacher, Long> implements ITeacherService {

    @Autowired
    private ITeacherRepository repository;

    @Override
    public IGenericRepo<Teacher, Long> getRepo(){
        return repository;
    }

}
