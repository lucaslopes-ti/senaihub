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
public class CursoInvalidoLarissa {
    @Mock
    private CursoRepository cursoRepository;

    // 3. @InjectMocks injeta o falso CursoRepository dentro do nosso CursoService de verdade.
    @InjectMocks
    private CursoService cursoService;
    
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
