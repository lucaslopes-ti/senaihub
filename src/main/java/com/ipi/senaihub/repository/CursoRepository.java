package com.ipi.senaihub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframewrok.stereotype.Repository;
import com.ipi.senaihub.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}