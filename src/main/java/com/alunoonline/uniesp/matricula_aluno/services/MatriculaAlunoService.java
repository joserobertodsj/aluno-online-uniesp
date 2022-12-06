package com.alunoonline.uniesp.matricula_aluno.services;

import com.alunoonline.uniesp.matricula_aluno.enums.Status;
import com.alunoonline.uniesp.matricula_aluno.enums.StatusNotas;
import com.alunoonline.uniesp.matricula_aluno.exceptions.MatriculaNaoEncontrada;
import com.alunoonline.uniesp.matricula_aluno.exceptions.TrancamentoIndisponivel;
import com.alunoonline.uniesp.matricula_aluno.models.MatriculaAlunoModel;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.DisciplinasAlunoDto;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.HistoricoAlunoDto;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoAluno;
import com.alunoonline.uniesp.matricula_aluno.models.dtos.MatriculaAlunoDtoProfessor;
import com.alunoonline.uniesp.matricula_aluno.repositories.MatriculaAlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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


    public MatriculaAlunoModel atualizarStatusMatricula(Long matriculaAlunoId){
        Optional<MatriculaAlunoModel> matriculaAlunoModelOptional = matriculaAlunoRepository.findById(matriculaAlunoId);
        matriculaAlunoModelOptional.orElseThrow(() -> new MatriculaNaoEncontrada("Matrícula não encontrada"));

        if(matriculaAlunoModelOptional.get().getNota1() == null || matriculaAlunoModelOptional.get().getNota2() == null){
            matriculaAlunoModelOptional.get().setStatus(Status.TRANCADA);
        }else {
            throw new TrancamentoIndisponivel("O trancamento só pode ser realizado se não houverem notas lançadas no sistema!");
        }

        return matriculaAlunoRepository.save(matriculaAlunoModelOptional.get());
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


    public HistoricoAlunoDto listarHistoricoAluno(Long alunoId){
        List<MatriculaAlunoModel> matriculasAluno = matriculaAlunoRepository.findByAlunoId(alunoId);

        if (!matriculasAluno.isEmpty()){
            var historico = new HistoricoAlunoDto();

            historico.setNomeAluno(matriculasAluno.get(0).getAluno().getNome());
            historico.setCursoAluno(matriculasAluno.get(0).getAluno().getCurso());
            List<DisciplinasAlunoDto>disciplinasAlunoDtoList = new ArrayList<>();

            for(MatriculaAlunoModel matricula : matriculasAluno){
                var disciplinasAlunoDto = new DisciplinasAlunoDto();
                disciplinasAlunoDto.setNomeDisciplina(matricula.getDisciplina().getNome());
                disciplinasAlunoDto.setProfessorDisciplina(matricula.getDisciplina().getProfessor().getNome());
                disciplinasAlunoDto.setNota1(matricula.getNota1());
                disciplinasAlunoDto.setNota2(matricula.getNota2());

                if (matricula.getNota1() != null && matricula.getNota2() != null){
                    disciplinasAlunoDto.setMedia((matricula.getNota1() + matricula.getNota2()) / 2);

                }else {
                    disciplinasAlunoDto.setMedia(null);
                }
                disciplinasAlunoDto.setStatusNotas(matricula.getStatusNotas());
                disciplinasAlunoDto.setStatus(matricula.getStatus());

                disciplinasAlunoDtoList.add(disciplinasAlunoDto);
            }

            historico.setDisciplinasAlunoDtoList(disciplinasAlunoDtoList);
            return historico;

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O aluno não foi matriculado em nenhuma disciplina!");

        }
    }




}
