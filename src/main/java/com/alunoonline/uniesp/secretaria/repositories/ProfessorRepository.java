package com.alunoonline.uniesp.secretaria.repositories;

import com.alunoonline.uniesp.secretaria.models.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {

}
