package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

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
                )).collect(Collectors.toList());
    }
}
