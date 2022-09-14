package com.ja.spring.data.jpa.controller;

import com.ja.spring.data.jpa.dto.TeacherDTO;
import com.ja.spring.data.jpa.entity.Teacher;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.service.ITeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/registerTeacher")
    public ResponseEntity<TeacherDTO> register(@Valid @RequestBody TeacherDTO teacherDTO) throws Exception{

        Teacher teacher = teacherService.register(mapper.map(teacherDTO, Teacher.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(teacher.getTeacherId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/listTeacherById/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable("id") Long id) throws Exception{

        Teacher teacher = teacherService.findById(id).orElseThrow(()-> new ModelNotFoundException("Model not with id: "+id+" found"));

        TeacherDTO teacherDTO = mapper.map(teacher, TeacherDTO.class);

        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @GetMapping("/listAllTeachers")
    public ResponseEntity<List<TeacherDTO>> findAll() throws Exception{

        List<TeacherDTO> teachers = teacherService.listAll()
                .stream().map(t -> mapper.map(t, TeacherDTO.class)).collect(Collectors.toList());

        if(teachers == null){
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PutMapping("/updateTeacher")
    public ResponseEntity<TeacherDTO> update(@Valid @RequestBody TeacherDTO teacherDTO)throws Exception{
        Teacher teacher = teacherService.findById(teacherDTO.getTeacherId()).orElseThrow(() -> new ModelNotFoundException("Model with id: "+teacherDTO.getTeacherId()+" not found"));

        Teacher teacherPersisted = teacherService.register(mapper.map(teacherDTO, Teacher.class));
        TeacherDTO teacherResponse = mapper.map(teacherPersisted, TeacherDTO.class);
        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id)throws Exception{
        Teacher teacher = teacherService.findById(id).orElseThrow(()-> new ModelNotFoundException("Model not with id: "+id+" found"));
        teacherService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
