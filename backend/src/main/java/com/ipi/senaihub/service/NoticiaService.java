// crie o modelo de negocio utilizando esses metodos findAll noticias, publicar noticias
// 1. Classe de serviço para a entidade Curso, responsável por conter a lógica de negócios relacionada aos cursos.

package com.ipi.senaihub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipi.senaihub.model.Noticia;
import com.ipi.senaihub.repository.NoticiaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository; // 2. Injeção de dependência do repositório de cursos para acessar os dados do banco.

    public Page<Noticia> listarNoticias(Pageable pageable) {
        return noticiaRepository.findAll(pageable); // 3. Método para listar todas as Noticias, utiliza o método findAll do repositório.
    }

    public Noticia publicarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia); // 4. Método para salvar uma Noticia, utiliza o método save do repositório.
    }
}




