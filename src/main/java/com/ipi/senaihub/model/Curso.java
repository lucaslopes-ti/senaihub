package com.ipi.senaihub.model;

import jakarta.persistence.*;

// 1. Classe curso vai ser uma entidade do banco de dados, uma tabela no banco
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modalidade;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricaoTecnica;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @Column(nullable = false)
    private Integer totalVagas;

    public Curso() {}

    public Curso(long l, String string, String string2, int i, String string3, int j) {}
  
    public Curso(Long id, String nome, String modalidade, String descricaoTecnica, Integer cargaHoraria,Integer totalVagas) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
        this.descricaoTecnica = descricaoTecnica;
        this.cargaHoraria = cargaHoraria;
        this.totalVagas = totalVagas;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getNome() { return nome; }
    public void setNome(String nome) {this.nome = nome;}

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public String getDescricaoTecnica() { return descricaoTecnica; }
    public void setDescricaoTecnica(String descricaoTecnica) { this.descricaoTecnica = descricaoTecnica;}

    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public Integer getTotalVagas() { return totalVagas; }
    public void setTotalVagas(Integer totalVagas) { this.totalVagas = totalVagas; }
    
}
