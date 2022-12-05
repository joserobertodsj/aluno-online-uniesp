package com.alunoonline.uniesp.matricula_aluno.controllers;

import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoAluno;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoProfessor;
import com.alunoonline.uniesp.matricula_aluno.services.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas-alunos")
public class MatriculaAlunoController {

    @Autowired
    private MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    public ResponseEntity<Object> salvarMatriculaAluno (@RequestBody MatriculaAlunoDtoAluno matriculaAlunoDtoAluno){
        matriculaAlunoService.salvar(matriculaAlunoDtoAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Matrícula efetuada com sucesso!");
    }

    @PatchMapping("/atualizacao-notas/{id}")
    public ResponseEntity<Object> atualizarInformacoesProfessor(
            @PathVariable(value = "id") Long matriculaAlunoId,
            @RequestBody MatriculaAlunoDtoProfessor matriculaAlunoDtoProfessor){
        matriculaAlunoService.atualizarInformacoesProfessor(matriculaAlunoId, matriculaAlunoDtoProfessor);
        return ResponseEntity.status(HttpStatus.OK).body("Atualizações realizadas com sucesso!");
    }

}
