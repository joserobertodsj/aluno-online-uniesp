package com.alunoonline.uniesp.controllers;

import com.alunoonline.uniesp.models.AlunoModel;
import com.alunoonline.uniesp.models.dtos.AlunoDto;
import com.alunoonline.uniesp.services.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> salvarAluno (@RequestBody @Valid AlunoDto alunoDto){

        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoModel));
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Long id){
        Optional<AlunoModel> alunoModelOptional = alunoService.buscarPorId(id);
        if(!alunoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
       }
        return ResponseEntity.status(HttpStatus.OK).body(alunoModelOptional.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable(value = "id") Long id){
        Optional<AlunoModel> alunoModelOptional = alunoService.buscarPorId(id);
        if (!alunoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        alunoService.delete(alunoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAluno(@PathVariable(value = "id") Long id, @RequestBody @Valid AlunoDto alunoDto ){
        Optional<AlunoModel> alunoModelOptional = alunoService.buscarPorId(id);
        if (!alunoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        var alunoModel = alunoModelOptional.get();

        BeanUtils.copyProperties(alunoDto, alunoModel);
        alunoModel.setId(alunoModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.save(alunoModel));



    }



}
