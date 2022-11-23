package com.alunoonline.uniesp.matricula_aluno.services;

import com.alunoonline.uniesp.matricula_aluno.repositories.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaAlunoService {

    @Autowired
    private MatriculaAlunoRepository matriculaAlunoRepository;


}
