package com.alunoonline.uniesp.secretaria.models.dtos;

import com.alunoonline.uniesp.secretaria.models.ProfessorModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DisciplinaDto {

    @NotBlank
    private String nome;


    private ProfessorModel professor;
}
