package com.ipi.senaihub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.ipi.senaihub.model.Noticia;
import com.ipi.senaihub.service.NoticiaService;

// 1. Controlador REST para a entidade Noticia, responsável por expor os endpoints relacionados as noticias.
@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public ResponseEntity<Page<Noticia>> listarNoticias(Pageable pageable) {
        return ResponseEntity.ok(noticiaService.listarNoticias(pageable)); // 2. Endpoint para listar todas as noticias, retorna uma resposta HTTP 200 OK com a lista de noticia no corpo da resposta.
    }

    @PostMapping
    public ResponseEntity<Noticia> salvarNoticia(@RequestBody Noticia noticia) {
        Noticia noticiaSalva = noticiaService.publicarNoticia(noticia); // 3. Endpoint para salvar uma nova noticia, recebe um objeto Noticia no corpo da requisição, chama o serviço para salvar a noticia e retorna uma resposta HTTP 201 Created com a noticia salva no corpo da resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(noticiaSalva);
    }
}