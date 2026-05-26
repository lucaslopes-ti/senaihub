// Cria com id, nomeCompleto, email, senha, nivelAcesso, podePostarNoticia

package com.ipi.senaihub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Enumerated vai consultar o JPA para salvar o texto do enum ROLE_ADMIN ou ROLE_USER no banco de dados, ao invés de salvar o número inteiro correspondente a cada valor do enum.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role nivelAcesso;

    @Column(nullable = false)
    private Boolean podePostarNoticias;

    public Usuario() {}

    public Usuario(Long id, String nomeCompleto, String email, String senha, Role nivelAcesso, Boolean podePostarNoticias) {
        
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
        this.podePostarNoticias = podePostarNoticias;
        
    } 

    //GETS AND SETS

    public Long getId(){ return id;}
    public void setId(Long id){ this.id = id;}

    public String getNomeCompleto(){ return nomeCompleto;}
    public void setNomeCompleto(String nomeCompleto){ this.nomeCompleto = nomeCompleto;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getSenha(){return senha;}
    public void setSenha(String senha){ this.senha = senha;}

    public Role getNivelAcesso(){ return nivelAcesso;}
    public void setNivelAcesso(Role nivelAcesso){ this.nivelAcesso = nivelAcesso;}

    public Boolean getpodePostarNoticias(){ return podePostarNoticias;}
    public void setpodePostarNoticias(Boolean podePostarNoticias){this.podePostarNoticias = podePostarNoticias;}

}


