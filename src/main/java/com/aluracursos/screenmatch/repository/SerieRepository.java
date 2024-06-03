package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);
    // top 5
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    // lista por categoria
    List<Serie> findByGenero(Categoria categoria);


}
