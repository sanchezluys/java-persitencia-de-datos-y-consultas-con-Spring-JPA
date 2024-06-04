package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.*;

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
                    4 - Buscar series por titulo
                    5 - Top 5 mejores series
                    6 - Buscar series por categoria
                    7 - Lista por SQL @query: menos 6 temp y mas de 7.5
                    8 - Lista usando JPQL (temporadas y evaluacion)
                    9 - Episodios por Nombre con JPQL
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
                case 4:
                    buscarSeriesPorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                    break;
                case 6:
                    buscarSeriesPorCategoria();
                    break;
                case 7:
                    ListaPersonalizada();
                    break;
                case 8:
                    ListaPersonalizadaJPQL();
                    break;
                case 9:
                    EpisodioNombreJPQL();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void EpisodioNombreJPQL() {
        System.out.println("Episodios por Nombre");
        System.out.println("Ingrese el nombre del episodio: ");
        var titulo = teclado.nextLine();
        List<Episodio> episodiosNombre = repositorio.episodiosPorNombre(titulo);
        episodiosNombre.forEach(System.out::println);
    }

    private void ListaPersonalizadaJPQL() {
        System.out.println("Lista personalizada usando JPQL");
        System.out.println("Ingrese el numero de temporadas maximas: ");
        var temporadas = teclado.nextInt();
        System.out.println("Ingrese la evaluacion minima: ");
        var evaluacion = teclado.nextDouble();
        List<Serie> filtroJPQL=repositorio.seriesTemporadasEvaluacionJPQL(temporadas, (float) evaluacion);
        filtroJPQL.forEach(System.out::println);
    }

    private void ListaPersonalizada() {
        System.out.println("Lista de Series buscadas con sql nativo menos 6 temp y evaluacion mayor 7.5");
        List<Serie> listaSQL= repositorio.seriesTemporadaEvaluacion();
        listaSQL.forEach(System.out::println);
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Escriba el genero/categoria a buscar: ");
        var genero = teclado.nextLine();
        var categoria= Categoria.fromEspanol(genero);
        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
        System.out.println("Las series de la categoria: " + genero);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarTop5Series() {
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s->
                System.out.println("Serie "+ s.getTitulo()+ " Evaluacion: "+ s.getEvaluacion()));
    }

    private void buscarSeriesPorTitulo() {
        System.out.println("Escribe el nombre de la serie que desea buscar ");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);
        if(serieBuscada.isPresent()){
            System.out.println("La serie buscada es: "+ serieBuscada.get());
        }
        else{
            System.out.println("Serie no encontrada");
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

