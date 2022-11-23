package com.alunoonline.uniesp.secretaria.repositories;

import com.alunoonline.uniesp.secretaria.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
}
