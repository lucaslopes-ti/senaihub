package com.ipi.senaihub;

import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;
import com.ipi.senaihub.service.CursoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// 1. @ExtendWith(MockitoExtension.class) habilita o Mockito neste teste.
// O Mockito é usado para "fingir" (mock) partes do sistema que não queremos testar de verdade agora (ex: o Banco de Dados).
@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    // 2. @Mock cria um objeto falso (um dublê) do CursoRepository. 
    // Assim não tocamos no banco de dados real.
    @Mock
    private CursoRepository cursoRepository;

    // 3. @InjectMocks injeta o falso CursoRepository dentro do nosso CursoService de verdade.
    @InjectMocks
    private CursoService cursoService;

    // 4. @Test indica ao JUnit que este método é um caso de teste.
    @Test
    public void deveLancarExcecaoQuandoVagasForemZeroOuMenos() {
        // 5. Preparação (Arrange): Criamos um curso com vagas igual a zero.
        Curso cursoInvalido = new Curso();
        cursoInvalido.setNome("Curso de Teste");
        cursoInvalido.setTotalVagas(0); // <--- Inválido segundo nossa regra de negócio

        // 6. Ação e Verificação (Act & Assert): Dizemos ao JUnit para tentar executar salvarCurso e esperar que ele dê erro (IllegalArgumentException).
        IllegalArgumentException excecao = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> cursoService.salvarCurso(cursoInvalido)
        );

        // 7. Checamos se a mensagem de erro que nossa regra de negócio gerou foi exatamente esta.
        Assertions.assertEquals("O total de vagas deve ser maior que zero.", excecao.getMessage());
        
        // 8. Opcional: Garantir que o repositório de fato NUNCA foi chamado (pois falhou antes na regra).
        verify(cursoRepository, never()).save(any(Curso.class));
    }

    @Test
    public void deveSalvarCursoComSucesso() {
        // 9. Preparação (Arrange): Curso válido.
        Curso cursoValido = new Curso();
        cursoValido.setId(1L);
        cursoValido.setNome("Java Spring");
        cursoValido.setModalidade("EAD");
        cursoValido.setDescricaoTecnica("Curso Backend");
        cursoValido.setCargaHoraria(40);
        cursoValido.setTotalVagas(30);
        
        // 10. Como o Repositório é Mock (Falso), ensinamos a ele o que fazer:
        // "Quando chamarem o seu método save(), retorne o cursoValido."
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoValido);

        // 11. Ação (Act): Chamamos o serviço de verdade.
        Curso resultado = cursoService.salvarCurso(cursoValido);

        // 12. Verificação (Assert): Verificamos se o serviço nos devolveu os dados corretos.
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Java Spring", resultado.getNome());
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
