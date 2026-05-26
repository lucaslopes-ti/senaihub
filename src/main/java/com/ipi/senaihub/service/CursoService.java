package com.ipi.senaihub.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;

// 1. Classe de serviço para a entidade Curso, responsável por conter a lógica de negócios relacionada aos cursos.
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository; // 2. Injeção de dependência do repositório de cursos para acessar os dados do banco.

    public List<Curso> listarCursos() {
        return cursoRepository.findAll(); // 3. Método para listar todos os cursos, utiliza o método findAll do repositório.
    }

    public Curso salvarCurso(Curso curso) {
        if (curso.getTotalVagas() == null || curso.getTotalVagas() <= 0) {
            throw new IllegalArgumentException("O total de vagas deve ser maior que zero."); // 4. Validação para garantir que o número total de vagas seja positivo.
        }

        return cursoRepository.save(curso); // 4. Método para salvar um curso, utiliza o método save do repositório.
    }
    
}