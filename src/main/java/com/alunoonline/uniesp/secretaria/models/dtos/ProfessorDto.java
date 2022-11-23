package com.alunoonline.uniesp.secretaria.models.dtos;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ProfessorDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;
}
