package com.alunoonline.uniesp.services;

import com.alunoonline.uniesp.models.DisciplinaModel;
import com.alunoonline.uniesp.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Object save(DisciplinaModel disciplinaModel) {
        return disciplinaRepository.save(disciplinaModel);
    }
}
