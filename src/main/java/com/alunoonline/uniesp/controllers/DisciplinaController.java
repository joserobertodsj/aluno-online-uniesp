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
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<DisciplinaModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Long id){
        Optional<DisciplinaModel> disciplinaModelOptional = disciplinaService.buscarPorId(id);

        if (!disciplinaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disciplina não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaModelOptional.get());
    }


}
