package com.ipi.senaihub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String corpoTexto;

    @Column(nullable = false)
    private LocalDateTime dataNoticia;

    private String urlImagemCapa;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    public Noticia() {}

    public Noticia(Long id, String titulo, String corpoTexto, LocalDateTime dataNoticia, String urlImagemCapa, Usuario autor){

        this.id = id;
        this.titulo = titulo;
        this.corpoTexto = corpoTexto;
        this.dataNoticia = dataNoticia;
        this.urlImagemCapa = urlImagemCapa;
        this.autor = autor;
        
    }        

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setCorpoTexto(String corpoTexto){ this.corpoTexto = corpoTexto; }
    public String getCorpoTexto(){ return corpoTexto; }

    public LocalDateTime getDataNoticia() { return dataNoticia; }
    public void setDataNoticia(LocalDateTime dataNoticia) { this.dataNoticia = dataNoticia; }

    public String getUrlImagemCapa() { return urlImagemCapa; }
    public void setUrlImagemCapa(String urlImagemCapa) { this.urlImagemCapa = urlImagemCapa; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

}