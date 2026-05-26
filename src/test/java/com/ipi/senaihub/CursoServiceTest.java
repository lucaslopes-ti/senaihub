package com.ipi.senaihub;

import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;
import com.ipi.senaihub.service.CursoService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    public void deveLancarExcecaoQuandoVagasForemZeroOuMenos() {
        Curso cursoInvalido = new Curso();
        cursoInvalido.setNome("Curso de Teste");
        cursoInvalido.setTotalVagas(0);

        IllegalArgumentException excecao = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> cursoService.salvarCurso(cursoInvalido)
        );

        Assertions.assertEquals("O total de vagas deve ser maior que zero.", excecao.getMessage());
        verify(cursoRepository, never()).save(any(Curso.class));
    }

    @Test
    public void deveSalvarCursoComSucesso() {
        Curso cursoValido = new Curso();
        cursoValido.setId(1L);
        cursoValido.setNome("Java Spring");
        cursoValido.setModalidade("EAD");
        cursoValido.setDescricaoTecnica("Curso Backend");
        cursoValido.setCargaHoraria(40);
        cursoValido.setTotalVagas(30);

        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoValido);

        Curso resultado = cursoService.salvarCurso(cursoValido);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Java Spring", resultado.getNome());
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    public void deveLancarExcecaoQuandoVagasForemNegativas() {
        Curso cursoInvalido = new Curso();
        cursoInvalido.setNome("Curso de Teste");
        cursoInvalido.setTotalVagas(-5);

        IllegalArgumentException excecao = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> cursoService.salvarCurso(cursoInvalido)
        );

        Assertions.assertEquals("O total de vagas deve ser maior que zero.", excecao.getMessage());
        verify(cursoRepository, never()).save(any(Curso.class));
    }

    @Test
    public void deveListarCursosRetornandoRepositorio() {
        List<Curso> cursos = List.of(
            new Curso(1L, "Java", "EAD", "Curso", 20, 10),
            new Curso(2L, "Python", "Presencial", "Curso", 30, 15)
        );

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<Curso> resultado = cursoService.listarCursos();

        Assertions.assertEquals(2, resultado.size());
        verify(cursoRepository, times(1)).findAll();
    }
}
