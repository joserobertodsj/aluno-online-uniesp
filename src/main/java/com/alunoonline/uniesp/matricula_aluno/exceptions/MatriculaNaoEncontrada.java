package com.alunoonline.uniesp.matricula_aluno.exceptions;

public class MatriculaNaoEncontrada extends RuntimeException{
    public MatriculaNaoEncontrada (String mensagemDeErro){
        super(mensagemDeErro);
    }
}
