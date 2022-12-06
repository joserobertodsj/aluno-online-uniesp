package com.alunoonline.uniesp.matricula_aluno.models.dtos;

import com.alunoonline.uniesp.matricula_aluno.enums.Status;
import com.alunoonline.uniesp.matricula_aluno.enums.StatusNotas;
import lombok.Data;

@Data
public class DisciplinasAlunoDto {
    private String nomeDisciplina;
    private String professorDisciplina;
    private Double nota1;
    private Double nota2;
    private Double media;
    private StatusNotas statusNotas;
    private Status status;
}
