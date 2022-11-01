package com.alunoonline.uniesp.services;

import com.alunoonline.uniesp.models.AlunoModel;
import com.alunoonline.uniesp.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;




    @Transactional
    public AlunoModel save(AlunoModel alunoModel) {
        return alunoRepository.save(alunoModel);
    }

    public List<AlunoModel> buscarTodos(){
        return alunoRepository.findAll();
    }


    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }


    @Transactional
    public void delete(AlunoModel alunoModel) {
        alunoRepository.delete(alunoModel);
    }
}


