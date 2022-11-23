package com.alunoonline.uniesp.secretaria.models;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "tb_aluno")
public class AlunoModel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 130)
    private String nome;

    @Column(nullable = false, length = 130)
    private String email;

    @Column(nullable = false, length = 130)
    private String curso;
}
