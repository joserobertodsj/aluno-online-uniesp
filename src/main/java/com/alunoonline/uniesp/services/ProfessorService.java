package com.alunoonline.uniesp.services;


import com.alunoonline.uniesp.models.ProfessorModel;
import com.alunoonline.uniesp.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Object save(ProfessorModel professorModel) {
        return professorRepository.save(professorModel);
    }
}
