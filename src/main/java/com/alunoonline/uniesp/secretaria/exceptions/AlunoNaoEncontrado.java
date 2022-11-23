package com.alunoonline.uniesp.secretaria.exceptions;

public class AlunoNaoEncontrado  extends RuntimeException{
    public AlunoNaoEncontrado(String mensagemDeErro){
        super(mensagemDeErro);
    }
}
