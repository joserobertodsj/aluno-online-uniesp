package com.alunoonline.uniesp.controllers;


import com.alunoonline.uniesp.models.ProfessorModel;
import com.alunoonline.uniesp.models.dtos.ProfessorDto;
import com.alunoonline.uniesp.services.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @PostMapping
    public ResponseEntity<Object> salvarProfessor (@RequestBody @Valid ProfessorDto professorDto){

        var professorModel = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorModel));

    }
}
