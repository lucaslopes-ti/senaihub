package com.ipi.senaihub;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;
import com.ipi.senaihub.service.CursoService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IsraelTest {

    @Mock // Cria um objeto falso do tipo CursoRepository, que será usado para simular o comportamento do repositório de cursos durante os testes.
    private CursoRepository cursoRepository;

    @InjectMocks // Cria uma instância da classe CursoService e injeta o objeto falso do CursoRepository nela, permitindo que os testes sejam realizados sem depender de um banco de dados real.
    private CursoService cursoService;


    @Test
    public void israelTest(){
        List<Curso> cursos = List.of(new Curso(1L,"Java","Curso",20,"EAD",10), new Curso(2L,"Python","Curso",30,"Presencial",15));

        Mockito.when(cursoRepository.findAll()).thenReturn(cursos);

        List<Curso> resultado = cursoService.listarCursos();

        Assertions.assertEquals(2, resultado.size());
    }
}
