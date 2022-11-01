package com.alunoonline.uniesp.services;

import com.alunoonline.uniesp.models.DisciplinaModel;
import com.alunoonline.uniesp.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Object save(DisciplinaModel disciplinaModel) {
        return disciplinaRepository.save(disciplinaModel);
    }

    public List<DisciplinaModel> buscarTodos() {
        return disciplinaRepository.findAll();
    }

    public Optional<DisciplinaModel> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void delete(DisciplinaModel disciplinaModel) {
        disciplinaRepository.delete(disciplinaModel);
    }
}
