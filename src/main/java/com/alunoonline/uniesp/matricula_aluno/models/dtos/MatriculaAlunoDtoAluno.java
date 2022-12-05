package com.alunoonline.uniesp.matricula_aluno.models.dtos;

import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.DisciplinaModel;
import lombok.Data;

@Data
public class MatriculaAlunoDtoAluno {

    private AlunoModel aluno;

    private DisciplinaModel disciplina;
}
