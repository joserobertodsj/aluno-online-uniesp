package com.alunoonline.uniesp.matricula_aluno.repositories;

import com.alunoonline.uniesp.matricula_aluno.models.MatriculaAlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAlunoModel, Long> {


}
