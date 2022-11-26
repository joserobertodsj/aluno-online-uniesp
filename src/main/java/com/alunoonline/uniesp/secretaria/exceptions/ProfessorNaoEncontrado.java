package com.alunoonline.uniesp.secretaria.exceptions;

public class ProfessorNaoEncontrado extends RuntimeException{
    public ProfessorNaoEncontrado(String mensagemDeErro){
        super(mensagemDeErro);
    }
}
