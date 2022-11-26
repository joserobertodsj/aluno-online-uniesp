package com.alunoonline.uniesp.secretaria.exceptions;



public class DisciplinaNaoEncontrada extends RuntimeException{
    public DisciplinaNaoEncontrada (String mensagemDeErro){
        super(mensagemDeErro);
    }
}
