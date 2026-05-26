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
public class GeraldoTest {
    @Mock 
    private CursoRepository cursoRepository;

    @InjectMocks 
    private CursoService cursoService;

    @Test
    public void testeSalvarCursoValido() {
        Curso cursoValido = new Curso(1L, "Java Spring", "Curso Backend", 40, "EAD", 30);
        Mockito.when(cursoRepository.save(Mockito.any(Curso.class))).thenReturn(cursoValido);
        Curso resultado = cursoService.salvarCurso(cursoValido);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Java Spring", resultado.getNome());
    }

}
