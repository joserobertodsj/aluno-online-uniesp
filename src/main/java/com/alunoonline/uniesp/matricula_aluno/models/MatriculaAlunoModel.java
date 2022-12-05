package com.alunoonline.uniesp.matricula_aluno.models;

import com.alunoonline.uniesp.matricula_aluno.enums.Status;
import com.alunoonline.uniesp.matricula_aluno.enums.StatusNotas;
import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.DisciplinaModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_matriculaAluno")
public class MatriculaAlunoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplinaModel disciplina;

    @Column
    private Double nota1;
    @Column
    private Double nota2;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private StatusNotas statusNotas;







}
