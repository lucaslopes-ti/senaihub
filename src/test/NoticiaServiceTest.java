package br.com.neves.teste;

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
public class NoticiaServiceTest {
// atividade
    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @Test
    public void testListarNoticiasPaginadasDeveChamarRepositoryFindAll() {

        Pageable pageable = PageRequest.of(1, 5);
        
        Page<Noticia> paginaEsperada = new PageImpl<>(List.of(), pageable, 0);
        Mockito.when(noticiaRepository.findAll(pageable)).thenReturn(paginaEsperada);
        
        noticiaService.listarNoticiasPaginadas(pageable);
        
        Mockito.verify(noticiaRepository, Mockito.times(1)).findAll(pageable);
    }
}