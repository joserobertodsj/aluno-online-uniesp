package com.alunoonline.uniesp.services;


import com.alunoonline.uniesp.models.ProfessorModel;
import com.alunoonline.uniesp.repositories.ProfessorRepository;
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
    public Object save(ProfessorModel professorModel) {
        return professorRepository.save(professorModel);
    }


    public List<ProfessorModel> buscarTodos() {
        return professorRepository.findAll();
    }

    public Optional<ProfessorModel> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }


    @Transactional
    public void delete(ProfessorModel professorModel) {
        professorRepository.delete(professorModel);
    }
}
