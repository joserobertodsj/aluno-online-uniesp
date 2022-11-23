package com.alunoonline.uniesp.secretaria.models.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AlunoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String curso;
}
