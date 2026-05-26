package com.ipi.senaihub;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class CursoNoticiaTest {

    // Teste Carla

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @Test
    public void deveChamarRepositorioAoPublicarNoticia() {
        
        Noticia noticia = new Noticia();
        
        Mockito.when(noticiaRepository.save(Mockito.any(Noticia.class)))
               .thenReturn(noticia);
        
        noticiaService.publicarNoticia(noticia);
        
       
        Mockito.verify(noticiaRepository, Mockito.times(1)).save(noticia);
    }
    
}
