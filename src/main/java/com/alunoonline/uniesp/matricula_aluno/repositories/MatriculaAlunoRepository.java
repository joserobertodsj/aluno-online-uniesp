package com.alunoonline.uniesp.matricula_aluno.repositories;

import com.alunoonline.uniesp.matricula_aluno.models.MatriculaAlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAlunoModel, Long> {


    List<MatriculaAlunoModel> findByAlunoId(Long alunoId);
}
