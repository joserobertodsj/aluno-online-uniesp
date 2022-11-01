package com.alunoonline.uniesp.controllers;

import com.alunoonline.uniesp.models.DisciplinaModel;
import com.alunoonline.uniesp.models.dtos.DisciplinaDto;
import com.alunoonline.uniesp.services.DisciplinaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Object> salvarDisciplina (@RequestBody @Valid DisciplinaDto disciplinaDto){
        var disciplinaModel = new DisciplinaModel();
        BeanUtils.copyProperties(disciplinaDto, disciplinaModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.save(disciplinaModel));
    }


}
