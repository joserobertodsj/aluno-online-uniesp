package com.alunoonline.uniesp.secretaria.services;


import com.alunoonline.uniesp.secretaria.exceptions.ProfessorNaoEncontrado;
import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.ProfessorModel;
import com.alunoonline.uniesp.secretaria.models.dtos.ProfessorDto;
import com.alunoonline.uniesp.secretaria.repositories.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {


    @Autowired
    private ProfessorRepository professorRepository;


    @Transactional
    public ProfessorModel salvar(ProfessorDto professorDto) {
        var professorModel = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorModel);

        return professorRepository.save(professorModel);
    }


    public List<ProfessorModel> buscarTodos() {
        return professorRepository.findAll();
    }

    public ProfessorModel buscarPorId(Long id) {

        return professorRepository.findById(id).orElseThrow(() -> new ProfessorNaoEncontrado("Professor não encontrado!"));

    }

    public ProfessorModel atualizarProfessor(Long id, ProfessorDto professorDto){
        Optional<ProfessorModel> professorModelOptional = professorRepository.findById(id);
        professorModelOptional.orElseThrow(() -> new ProfessorNaoEncontrado("Professor não encontrado!"));

        var professorModel = professorModelOptional.get();
        BeanUtils.copyProperties(professorDto, professorModel);
        professorModel.setId(professorModelOptional.get().getId());
        return professorRepository.save(professorModel);
    }


    @Transactional
    public void delete(Long id) {
        Optional<ProfessorModel> professorModelOptional = professorRepository.findById(id);
        professorModelOptional.orElseThrow(() -> new ProfessorNaoEncontrado("Professor não encontrado!"));
        professorRepository.deleteById(id);
    }
}
