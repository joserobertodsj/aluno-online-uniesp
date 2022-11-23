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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDisciplina(@PathVariable(value = "id") Long id){
        Optional<DisciplinaModel> disciplinaModelOptional = disciplinaService.buscarPorId(id);

        if (!disciplinaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disciplina não encontrada!");
        }
        disciplinaService.delete(disciplinaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Disciplina deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarDisciplina(@PathVariable(value = "id") Long id, @RequestBody @Valid DisciplinaDto disciplinaDto){
        Optional<DisciplinaModel> disciplinaModelOptional = disciplinaService.buscarPorId(id);

        if (!disciplinaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disciplina não encontrada!");
        }

        var disciplinaModel = new DisciplinaModel();
        BeanUtils.copyProperties(disciplinaDto, disciplinaModel);

        disciplinaModel.setId(disciplinaModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.save(disciplinaModel));
    }


}
