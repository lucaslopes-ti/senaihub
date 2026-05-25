// crie o modelo de negocio utilizando esses metodos findAll noticias, publicar noticias
// 1. Classe de serviço para a entidade Curso, responsável por conter a lógica de negócios relacionada aos cursos.

package com.ipi.senaihub.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipi.senaihub.model.Noticia;
import com.ipi.senaihub.repository.NoticiaRepository;
import com.springframework.data.domain.Pageable;
import com.springframework.data.domain.Page;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository NoticiaRepository; // 2. Injeção de dependência do repositório de cursos para acessar os dados do banco.

    public Page<Noticia> listarNoticia(Pageable pageable) {
        return NoticiaRepository.findAll(pageable); // 3. Método para listar todas as Noticias, utiliza o método findAll do repositório.
    }

    public Noticia publicarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia); // 4. Método para salvar uma Noticia, utiliza o método save do repositório.
    }
}




