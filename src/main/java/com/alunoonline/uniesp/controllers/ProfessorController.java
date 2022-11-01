package com.alunoonline.uniesp.controllers;


import com.alunoonline.uniesp.models.ProfessorModel;
import com.alunoonline.uniesp.models.dtos.ProfessorDto;
import com.alunoonline.uniesp.services.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<ProfessorModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId (@PathVariable(value = "id") Long id){
        Optional<ProfessorModel> professorModelOptional = professorService.buscarPorId(id);

        if(!professorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professorModelOptional.get());
    }
}
