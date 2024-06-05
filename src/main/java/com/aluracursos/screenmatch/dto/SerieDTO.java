package com.aluracursos.screenmatch.dto;

import com.aluracursos.screenmatch.model.Categoria;

public record SerieDTO
        (
            String      titulo,
            Integer     totalTemporadas,
            Double      evaluacion,
            String      poster,
            String      actores,
            String      sinopsis,
            Categoria   genero
        )
{

}
