package com.ipi.senaihub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.service.CursoService;

// 1. Controlador REST para a entidade Curso, responsável por expor os endpoints relacionados aos cursos.
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos()); // 2. Endpoint para listar todos os cursos, retorna uma resposta HTTP 200 OK com a lista de cursos no corpo da resposta. 
    }

    @PostMapping
    public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
        Curso cursoSalvo = cursoService.salvarCurso(curso); // 3. Endpoint para salvar um novo curso, recebe um objeto Curso no corpo da requisição, chama o serviço para salvar o curso e retorna uma resposta HTTP 201 Created com o curso salvo no corpo da resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

}
