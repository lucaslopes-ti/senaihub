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
@ExtendWith(MockitoExtension.class) // Anotação para habilitar o suporte ao Mockito no JUnit 5, permitindo o uso de objetos falsos e injeção de dependências durante os testes.

public class CursoServiceTest {

    @Mock // Cria um objeto falso do tipo CursoRepository, que será usado para simular o comportamento do repositório de cursos durante os testes.
    private CursoRepository cursoRepository;

    @InjectMocks // Cria uma instância da classe CursoService e injeta o objeto falso do CursoRepository nela, permitindo que os testes sejam realizados sem depender de um banco de dados real.
    private CursoService cursoService;

    @Test
    public void deveLancarExcecaoQuandoVagasForemZeroOuMenos(){
        Curso curso = new Curso();
        curso.setNome("Curso de Teste");
        curso.setTotalVagas(0); // Define o número total de vagas como um valor negativo para testar a validação.
    
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            IllegalArgumentException exception.class,
            () -> cursoService.salvarCurso(curso)
        });

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
