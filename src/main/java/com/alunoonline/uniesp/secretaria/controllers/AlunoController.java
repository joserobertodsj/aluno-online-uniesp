package com.alunoonline.uniesp.secretaria.controllers;

import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.dtos.AlunoDto;
import com.alunoonline.uniesp.secretaria.services.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> salvarAluno (@RequestBody @Valid AlunoDto alunoDto){
        alunoService.salvar(alunoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable(value = "id") Long id){
       alunoService.delete(id);
       return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAluno(@PathVariable(value = "id") Long id, @RequestBody @Valid AlunoDto alunoDto ){
        alunoService.atualizarAluno(id, alunoDto);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno atualizado com sucesso!");
    }



}
