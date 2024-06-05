package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.SerieDTO;

import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {
    // agregando las anotaciones para conectarse al repositorio
    @Autowired
    private SerieRepository repositorio;

    @GetMapping("/series")

    public List<SerieDTO> obtenerTodasLasSeries(){
        return repositorio.findAll().stream()
                .map(s -> new SerieDTO(
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getActores(),
                        s.getSinopsis(),
                        s.getGenero()
                        ))
                        .collect(Collectors.toList());
    }
}
