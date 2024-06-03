package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;

import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    String clave2 = System.getenv("API_KEY");
    private final String API_KEY = "&apikey="+ clave2;
    private ConvierteDatos conversor = new ConvierteDatos();
    //clase 01-03
    private List<DatosSerie> datosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    // clase 03-04
    private List<Serie> series;

    public Principal(SerieRepository repository) {
        this.repositorio= repository;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------------------------------------------              
                    Buscador de series y episodios
                    ----------------------------------------------------------------    
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    ----------------------------------------------------------------              
                    0 - Salir
                    ----------------------------------------------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }



    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }
    private void buscarEpisodioPorSerie() {
        //DatosSerie datosSerie = getDatosSerie();
        // clase 03-04
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre de la serie, para buscar los episodios: ");
        var nombreSerie = teclado.nextLine();
        // trabajar con optional
        Optional<Serie>  serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();
        //
        if(serie.isPresent())
        {
            var serieEncontrada= serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);
            //
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d->d.episodios().stream()
                            .map(e-> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }




    }
    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        //datosSeries.add(datos);
        //* enviar el resultado a la bd
        System.out.println("Muestra datos: "+datos);

        Serie serie = new Serie(datos);

        System.out.println("Muestra serie "+ serie);
        //serie.setId(1L);
        repositorio.save(serie);
        System.out.println("Registro enviado a la BD...");
        //** luys revisando
        //System.out.println("datosSeries "+ datosSeries);
    }

    private void mostrarSeriesBuscadas()
    {
        series = repositorio.findAll();


        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);

    }


}

