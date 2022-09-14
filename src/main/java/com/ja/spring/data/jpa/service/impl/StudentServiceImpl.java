package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.dto.StudentDTO;
import com.ja.spring.data.jpa.entity.Student;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.repository.ICourseStudentRepository;
import com.ja.spring.data.jpa.repository.IGenericRepo;
import com.ja.spring.data.jpa.repository.IStudentRepository;
import com.ja.spring.data.jpa.service.IStudentService;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends CRUDImpl<Student, Long> implements IStudentService{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ICourseStudentRepository courseStudentRepository;

    @Override
    public IGenericRepo<Student, Long> getRepo() {
        return studentRepository;
    }

    @Override
    public void deleteEntity(Long aLong) throws Exception {
        studentRepository.findById(aLong).orElseThrow(()-> new ModelNotFoundException("Student with id: "+aLong+" not found"));
        courseStudentRepository.deleteByStudentId(aLong);
        super.deleteEntity(aLong);
    }

    @Override
    public Page<StudentDTO> listPageable(Pageable pageable) {
        Page<StudentDTO> students = studentRepository.findAll(pageable).map(s -> mapper.map(s, StudentDTO.class));
        return students;
    }
}
