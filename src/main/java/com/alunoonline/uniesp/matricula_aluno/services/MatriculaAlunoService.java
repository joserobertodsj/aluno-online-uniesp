package com.alunoonline.uniesp.matricula_aluno.services;

import com.alunoonline.uniesp.matricula_aluno.enums.Status;
import com.alunoonline.uniesp.matricula_aluno.enums.StatusNotas;
import com.alunoonline.uniesp.matricula_aluno.exceptions.MatriculaNaoEncontrada;
import com.alunoonline.uniesp.matricula_aluno.models.MatriculaAlunoModel;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoAluno;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoProfessor;
import com.alunoonline.uniesp.matricula_aluno.repositories.MatriculaAlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaAlunoService {

    @Autowired
    private MatriculaAlunoRepository matriculaAlunoRepository;

    public MatriculaAlunoModel salvar (MatriculaAlunoDtoAluno matriculaAlunoDtoAluno){
        var matriculaAluno = new MatriculaAlunoModel();
        matriculaAluno.setStatus(Status.MATRICULADO);
        BeanUtils.copyProperties(matriculaAlunoDtoAluno, matriculaAluno);
        return matriculaAlunoRepository.save(matriculaAluno);

    }

    public MatriculaAlunoModel atualizarInformacoesProfessor(Long matriculaAlunoId, MatriculaAlunoDtoProfessor matriculaAlunoDtoProfessor){
        Optional<MatriculaAlunoModel> matriculaAlunoModelOptional = matriculaAlunoRepository.findById(matriculaAlunoId);
        matriculaAlunoModelOptional.orElseThrow(() -> new MatriculaNaoEncontrada("Matrícula não encontrada!"));

        var matriculaAlunoModel = matriculaAlunoModelOptional.get();
        BeanUtils.copyProperties(matriculaAlunoDtoProfessor, matriculaAlunoModel);

        matriculaAlunoModel.setId(matriculaAlunoModelOptional.get().getId());
        matriculaAlunoModel.setAluno(matriculaAlunoModelOptional.get().getAluno());
        matriculaAlunoModel.setDisciplina(matriculaAlunoModelOptional.get().getDisciplina());

        if (matriculaAlunoModel.getNota1() != null && matriculaAlunoModel.getNota2() != null){

            if ((matriculaAlunoModel.getNota1() + matriculaAlunoModel.getNota2()) / 2 >= 7){
                matriculaAlunoModel.setStatusNotas(StatusNotas.APROVADO);
            }else {
                matriculaAlunoModel.setStatusNotas(StatusNotas.REPROVADO);
            }
        }

        return matriculaAlunoRepository.save(matriculaAlunoModel);
    }


}
