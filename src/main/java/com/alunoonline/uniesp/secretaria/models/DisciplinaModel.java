package com.alunoonline.uniesp.secretaria.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_disciplina")
public class DisciplinaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 225)
    private String nome;

    @ManyToOne //para muitas disciplinas eu tenho um professor
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;
}
