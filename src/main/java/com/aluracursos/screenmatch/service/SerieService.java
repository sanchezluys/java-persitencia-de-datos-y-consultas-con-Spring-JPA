package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obtenerTodasLasSeries(){
        return convierteDatos(repositorio.findAll());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repositorio.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return convierteDatos(repositorio.lanzamientosMasRecientes());
    }


    private List<SerieDTO> convierteDatos(List<Serie> serie) {
        return serie.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getActores(),
                        s.getSinopsis(),
                        s.getGenero()
                )).collect(Collectors.toList());
    }


    public SerieDTO obtenerSeriePorId(Long id) {
        Optional<Serie> serieEncontrada = repositorio.findById(id);
        if(serieEncontrada.isPresent()){
            Serie s= serieEncontrada.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getEvaluacion(),
                    s.getPoster(),
                    s.getActores(),
                    s.getSinopsis(),
                    s.getGenero());
        }
        else{
            return null;
        }
    }
}
