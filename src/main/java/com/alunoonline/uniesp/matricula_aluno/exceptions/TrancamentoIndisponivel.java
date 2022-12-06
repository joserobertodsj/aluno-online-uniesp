package com.alunoonline.uniesp.matricula_aluno.exceptions;

public class TrancamentoIndisponivel extends RuntimeException{
    public TrancamentoIndisponivel(String mensagemDeErro){
        super(mensagemDeErro);
    }
}
