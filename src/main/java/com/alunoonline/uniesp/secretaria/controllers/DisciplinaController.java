package com.alunoonline.uniesp.secretaria.controllers;

import com.alunoonline.uniesp.secretaria.services.DisciplinaService;
import com.alunoonline.uniesp.secretaria.models.DisciplinaModel;
import com.alunoonline.uniesp.secretaria.models.dtos.DisciplinaDto;
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
        disciplinaService.salvar(disciplinaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Disciplina cadastrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Long id){

        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDisciplina(@PathVariable(value = "id") Long id){
        disciplinaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Disciplina deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarDisciplina(@PathVariable(value = "id") Long id, @RequestBody @Valid DisciplinaDto disciplinaDto){
        disciplinaService.alualizarDisciplina(id, disciplinaDto);
        return ResponseEntity.status(HttpStatus.OK).body("Disciplina atualizada com sucesso!");
    }


}
