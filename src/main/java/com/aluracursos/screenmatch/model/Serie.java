package com.aluracursos.screenmatch.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;


@Entity
@Table(name = "series")
public class Serie {
    //** el id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //** se quiere que el nombre se la serie sea unico
    @Column(unique = true)
    private String titulo;
    //**
    private Integer totalTemporadas;
    private Double evaluacion;
    private String poster;
    private String actores;
    private String sinopsis;
    //** es enum
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    // ahora se mapea @Transient
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios;


    //** se debe colocar un constructor predeterminado
    public Serie(){}
    public Serie(DatosSerie datosSerie)
    {
        this.titulo = datosSerie.titulo();
        this.totalTemporadas = datosSerie.totalTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.poster = datosSerie.poster();
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis();
        // sin creditos en chatgpt
        //this.sinopsis = ConsultaChatGPT.obtenerTraduccion(datosSerie.sinopsis());

    }

    @Override
    public String toString() {
        return  "genero= " + genero +
                ", titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", evaluacion=" + evaluacion +
                ", poster='" + poster + '\'' +
                ", actores='" + actores + '\'' +
                ", sinopsis='" + sinopsis + '\''+
                ", episodios='" + episodios + '\'';
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e->e.setSerie(this));
        this.episodios = episodios;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }


}
