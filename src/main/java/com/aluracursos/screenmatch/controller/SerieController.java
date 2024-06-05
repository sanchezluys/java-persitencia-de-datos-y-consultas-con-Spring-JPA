package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    // agregando las anotaciones para conectarse al repositorio
    @Autowired
    private SerieRepository repositorio;

    @GetMapping("/series")

    public List<Serie> obtenerTodasLasSeries(){
        return repositorio.findAll();
    }
}
