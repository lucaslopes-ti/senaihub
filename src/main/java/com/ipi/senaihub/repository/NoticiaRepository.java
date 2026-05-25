package com.ipi.senaihub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipi.senaihub.model.Noticia;


@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
    
}

     