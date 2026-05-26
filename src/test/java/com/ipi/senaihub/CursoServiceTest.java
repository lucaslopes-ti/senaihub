package com.ipi.senaihub;

import com.ipi.senaihub.model.Curso;
import com.ipi.senaihub.repository.CursoRepository;
import com.ipi.senaihub.service.CursoService;
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

        Assertions.assertEquals("O número total de vagas não pode ser negativo ou zero.", exception.getMessage()); // Verifica se a mensagem da exceção lançada é a esperada, confirmando que a validação está funcionando corretamente.

        Mockito.verify(cursoRepository, Mockito.never()).save(Mockito.any(Curso.class)); // Verifica se o método save do cursoRepository nunca foi chamado, garantindo que o curso não foi salvo no banco de dados devido à validação falha.
    }
    //LARISSA: Crie o teste que valida a regra de negócio quando totalVagas é zero.
    @Test
    public void cursoInvalido(){
        Curso cursoInvalido = new Curso();
        cursoInvalido.setNome ("IPI");
        cursoInvalido.setTotalVagas(0);

        IllegalArgumentException excecao = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> cursoService.salvarCurso(cursoInvalido)
        );
        Assertions.assertEquals("O total de vagas deve ser maior que zero.", excecao.getMessage());

        Mockito.verify(cursoRepository, Mockito.never()).save(Mockito.any(Curso.class));
    }
}
