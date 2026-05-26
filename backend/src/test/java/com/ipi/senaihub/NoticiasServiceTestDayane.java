package com.ipi.senaihub;

import com.ipi.senaihub.model.Noticia;
import com.ipi.senaihub.repository.NoticiaRepository;
import com.ipi.senaihub.service.NoticiaService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class NoticiasServiceTestDayane {
    
    
    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @Test
    public void listarNoticiasPaginadas() {

        Pageable pageable = PageRequest.of(0, 2);

        List<Noticia> noticias = List.of(
                new Noticia(),
                new Noticia()
        );

        Page<Noticia> paginaEsperada =
                new PageImpl<>(noticias, pageable, noticias.size());

        Mockito.when(noticiaRepository.findAll(pageable))
                .thenReturn(paginaEsperada);

        Page<Noticia> resultado =
                noticiaService.listarNoticias(pageable);

        Assertions.assertEquals(2, resultado.getContent().size());
    }
}
