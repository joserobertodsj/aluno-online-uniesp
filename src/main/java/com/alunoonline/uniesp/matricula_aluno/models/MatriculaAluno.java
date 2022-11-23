package com.alunoonline.uniesp.matricula_aluno.models;

import com.alunoonline.uniesp.matricula_aluno.enums.SituacaoMatriculaAluno;
import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.DisciplinaModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_matriculaAluno")
public class MatriculaAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double nota1;

    @Column(nullable = false)
    private Double nota2;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplinaModel disciplina;

    @Enumerated(EnumType.STRING)
    private SituacaoMatriculaAluno situacaoMatriculaAluno;




}
