package com.alunoonline.uniesp.secretaria.services;

import com.alunoonline.uniesp.secretaria.exceptions.AlunoNaoEncontrado;
import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import com.alunoonline.uniesp.secretaria.models.dtos.AlunoDto;
import com.alunoonline.uniesp.secretaria.repositories.AlunoRepository;
import org.springframework.beans.BeanUtils;
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
    public AlunoModel salvar(AlunoDto alunoDto) {
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoModel);

        return alunoRepository.save(alunoModel);
    }

    public List<AlunoModel> buscarTodos(){
        return alunoRepository.findAll();
    }


    public AlunoModel buscarPorId(Long id){
        return alunoRepository.findById(id).orElseThrow(() -> new AlunoNaoEncontrado("Aluno não encontrado!"));
    }

    public AlunoModel atualizarAluno(Long id, AlunoDto alunoDto){
        Optional<AlunoModel> alunoModelOptional = alunoRepository.findById(id);
        alunoModelOptional.orElseThrow(() -> new AlunoNaoEncontrado("Aluno não encontrado!"));

        var alunoModel = alunoModelOptional.get();
        BeanUtils.copyProperties(alunoDto, alunoModel);
        alunoModel.setId(alunoModelOptional.get().getId());
        return alunoRepository.save(alunoModel);
    }

    @Transactional
    public void delete(Long id) {
        Optional<AlunoModel> alunoModelOptional =alunoRepository.findById(id);
        alunoModelOptional.orElseThrow(() -> new AlunoNaoEncontrado("Aluno não encontrado!"));
        alunoRepository.deleteById(id);
    }

}


