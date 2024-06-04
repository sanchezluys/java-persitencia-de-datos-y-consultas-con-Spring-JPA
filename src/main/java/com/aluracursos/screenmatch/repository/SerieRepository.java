package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    // reto lista menos de 3 temporadas y puntaje mayor a 7
    List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(Integer totalTemporadas, Double evaluacion);
    // querys nativas se usa una anotacion.
    @Query( value = "SELECT * FROM series WHERE series.total_temporadas <=6 and series.evaluacion >= 7.5", nativeQuery = true)
    List<Serie> seriesTemporadaEvaluacion();

    // usando JPQL
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :temporadas and s.evaluacion >= :evaluacion")
    List<Serie> seriesTemporadasEvaluacionJPQL(Integer temporadas, Float evaluacion);


}
