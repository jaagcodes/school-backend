package com.ja.spring.data.jpa.controller;

import com.ja.spring.data.jpa.dto.CourseMaterialDTO;
import com.ja.spring.data.jpa.entity.CourseMaterial;
import com.ja.spring.data.jpa.exception.ModelNotFoundException;
import com.ja.spring.data.jpa.service.ICourseMaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/courseMaterials")
public class CourseMaterialController {

    @Autowired
    private ICourseMaterialService materialService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/listCourseMaterial/{courseMaterialId}")
    public ResponseEntity<CourseMaterialDTO> listCourseMaterialById(@PathVariable("courseMaterialId") Long courseMaterialId)throws Exception{
        CourseMaterial foundedCourseMaterial = materialService.findById(courseMaterialId)
                .orElseThrow(()->new ModelNotFoundException("Course Material with id: "+courseMaterialId +"not found"));
        CourseMaterialDTO courseMaterialDTO = mapper.map(foundedCourseMaterial, CourseMaterialDTO.class);
        return new ResponseEntity<>(courseMaterialDTO, HttpStatus.OK);
    }

    @PutMapping("/updateCourseMaterial")
    public ResponseEntity<CourseMaterialDTO> updateCourseMaterial(@Valid @RequestBody CourseMaterialDTO courseMaterialDTO) throws Exception {
        CourseMaterial foundedCourseMaterial = materialService.findById(courseMaterialDTO.getCourseMaterialId())
                .orElseThrow(()-> new ModelNotFoundException("course material not found"));

        foundedCourseMaterial = mapper.map(courseMaterialDTO, CourseMaterial.class);
        courseMaterialDTO = mapper.map(materialService.register(foundedCourseMaterial), CourseMaterialDTO.class);
        return new ResponseEntity<>(courseMaterialDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourseMaterial/{courseMaterialId}")
    public ResponseEntity<Void> deleteCourseMaterial(@PathVariable("courseMaterialId") Long courseMaterialId)throws Exception{
        materialService.findById(courseMaterialId).orElseThrow(()-> new ModelNotFoundException("Course Material with id: "+courseMaterialId +"not found"));

        materialService.deleteEntity(courseMaterialId);
        return ResponseEntity.noContent().build();
    }
}
