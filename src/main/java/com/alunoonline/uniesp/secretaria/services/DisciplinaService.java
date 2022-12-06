package com.alunoonline.uniesp.secretaria.services;

import com.alunoonline.uniesp.secretaria.exceptions.DisciplinaNaoEncontrada;
import com.alunoonline.uniesp.secretaria.models.dtos.DisciplinaDto;
import com.alunoonline.uniesp.secretaria.repositories.DisciplinaRepository;
import com.alunoonline.uniesp.secretaria.models.DisciplinaModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @Transactional
    public DisciplinaModel salvar(DisciplinaDto disciplinaDto) {
        var disciplinaModel = new DisciplinaModel();

        BeanUtils.copyProperties(disciplinaDto, disciplinaModel);

        return disciplinaRepository.save(disciplinaModel);
    }

    public List<DisciplinaModel> buscarTodos() {

        return disciplinaRepository.findAll();
    }

    public DisciplinaModel buscarPorId(Long id) {

        return disciplinaRepository.findById(id).orElseThrow(() -> new DisciplinaNaoEncontrada("Disciplina não encontrada!"));
    }

    public DisciplinaModel alualizarDisciplina(Long id, DisciplinaDto disciplinaDto){
        Optional<DisciplinaModel> disciplinaModelOptional = disciplinaRepository.findById(id);
        disciplinaModelOptional.orElseThrow(() -> new DisciplinaNaoEncontrada("Disciplina não encontrada!"));

        var disciplinaModel = disciplinaModelOptional.get();
        BeanUtils.copyProperties(disciplinaDto, disciplinaModel);
        disciplinaModel.setId(disciplinaModelOptional.get().getId());
        return disciplinaRepository.save(disciplinaModel);
    }

    @Transactional
    public void delete(Long id) {
        Optional<DisciplinaModel> disciplinaModelOptional = disciplinaRepository.findById(id);
        disciplinaModelOptional.orElseThrow(() -> new DisciplinaNaoEncontrada("Disciplina não encontrada!"));
        disciplinaRepository.deleteById(id);
    }
}
