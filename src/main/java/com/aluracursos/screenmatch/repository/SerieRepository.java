package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
