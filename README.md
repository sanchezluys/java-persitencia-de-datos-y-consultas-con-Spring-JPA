![Programação-Java_ Persistencia de datos y consultas con Spring Data JPA](https://github.com/genesysR-dev/2066-java-persitencia-de-datos-y-consultas-con-Spring-JPA/assets/91544872/e0e3a9f8-afc7-4e7b-be83-469351ef2d70)

# ScreenMatch - WEB

Proyecto desarrollado durante el segundo curso de la formación Avanzando con Java de Alura

## 🔨 Objetivos del proyecto

* 

----------------------------------------------------------------

### Progreso del curso by sanchezluys parte 2: con srping web

| Clase | Descripción                                                                    | Estado    / Observaciones / Errores                                                                                              |
|-------|--------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| 01-04 | Utilizando Spring web                                                          |                                                                                                                                  |
|       | incializando la rama del nuevo proyecto                                        |                                                                                                                                  |
|       | limpiando las imagenes                                                         |                                                                                                                                  |
|       | Agregando dependencia web                                                      |                                                                                                                                  |
|       | spring-boot-starter-web                                                        | <artifactId>spring-boot-starter-web</artifactId>                                                                                 |
|       | se copia la clase screenmatchapplication Consola                               |                                                                                                                                  |
|       | es necesario no usar la interface commandlinerunner. en la nueva web           | ![img.png](img.png)                                                                                                              |
|       | se limpia el codigo y se verifica si el servidor tomcat se ejecuta             | asi se muestra el servicio tomcat corriendo en error                                                                             |
|       |                                                                                |                                                                                                                                  |
|       |                                                                                | Se puede definir el puerto en spring, agregando                                                                                  |
|       |                                                                                | al application.properties                                                                                                        |
|       |                                                                                | server.port=8081  por ejemplo.                                                                                                   |
|       |                                                                                |                                                                                                                                  |
| 01-07 | Mostrando un mensaje en el navegador                                           |                                                                                                                                  |
|       | MVC + API REST                                                                 |                                                                                                                                  |
|       | - se comenta todo del screenmatch de consola                                   |                                                                                                                                  |
|       | - se crea el nuevo paquete de controller                                       |                                                                                                                                  |
|       | - se crea la nueva clase SerieController                                       |                                                                                                                                  |
|       | -- se agregan las anotaciones en la clase:                                     |                                                                                                                                  |
|       | --- @RestController y @GetMapping                                              | ![img_1.png](img_1.png)                                                                                                          |
|       | --- en el controlador podemos enviar un mensaje al sitio web                   |                                                                                                                                  |
|       |                                                                                |                                                                                                                                  |
| 02-02 | Devolviendo series en el navegador                                             |                                                                                                                                  |
|       | se arregla el controlador para enviar al front el listado de series            |                                                                                                                                  |
|       | @Autowired, private SerieRepository repositorio; return repositorio.findAll(); | se presenta un error por busqueda circular en jackson databind                                                                   |
|       |                                                                                | stackoverflow                                                                                                                    |
|       |                                                                                | ErrorMvcAutoConfiguration$StaticView : Cannot render error page for request [/series] as the response has already been committed |
|       |                                                                                |                                                                                                                                  |


