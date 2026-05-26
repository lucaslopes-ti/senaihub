package com.ipi.senaihub;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito; 

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;

import com.ipi.senaihub.service.CursoService; 

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) 
public class CursoServiceTest {

    @Mock 
    private CursoRepository cursoRepository;

    @InjectMocks 
    private CursoService cursoService;

    // ---  Novo Teste do geraldo ---
    @Test
    public void deveLancarExcecaoQuandoVagasForemZeroOuMenos(){
        Curso curso = new Curso(5L, "Curso de Teste", "EAD", "Descrição de teste", 20, 0);
        curso.setNome("Curso de Teste");
        curso.setTotalVagas(0); 
    
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cursoService.salvarCurso(curso);
        });

        Assertions.assertEquals("O número total de vagas não pode ser negativo ou zero.", exception.getMessage()); 

        Mockito.verify(cursoRepository, Mockito.never()).save(Mockito.any(Curso.class)); 
    }
}