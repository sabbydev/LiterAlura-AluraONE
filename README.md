# LiterAlura

Literalura es una aplicación Java basada en consola que interactúa con la API de Project Gutenberg para buscar, listar y gestionar libros y autores. Este proyecto utiliza Spring Boot y JPA para gestionar una base de datos PostgreSQL donde se almacenan los libros y autores buscados.

## Funcionalidades

La aplicación permite realizar las siguientes operaciones desde un menú interactivo en consola:

1. **Buscar libro por título:** Consulta la API de Project Gutenberg para encontrar libros por título y almacena los resultados en la base de datos.
2. **Listar libros buscados anteriormente:** Muestra los libros que han sido almacenados en la base de datos.
3. **Listar libros por idioma:** Filtra los libros almacenados por su idioma.
4. **Listar autores de libros buscados:** Presenta los autores de los libros que han sido almacenados.
5. **Listar autores vivos en un año determinado:** Encuentra autores que estaban vivos en un año específico.
6. **Cantidad de libros por idioma:** Muestra el conteo de libros almacenados por idioma.

## Requisitos

- **Java 17** o superior.
- **PostgreSQL** instalado y configurado.
- IntelliJ IDEA (opcional pero recomendado).

## Configuración

### Variables de entorno

Se deben configurar las siguientes variables de entorno para conectar la aplicación con la base de datos:

- DB_HOST: Host donde se encuentra la base de datos (por ejemplo: localhost).
- DB_NAME: Nombre de la base de datos.
- DB_USER: Usuario de la base de datos.
- DB_PASSWORD: Contraseña del usuario de la base de datos.

En caso de usar IntelliJ IDEA, asegúrate de configurar estas variables en las opciones de ejecución del IDE.

### Base de datos

- Crea una base de datos PostgreSQL con el nombre especificado en DB_NAME.

La conexión a la base de datos se realiza automáticamente mediante JPA, que también generará las tablas necesarias.

## Ejecución

Clona el repositorio.

```
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```

Construye y ejecuta la aplicación utilizando Maven:

```
mvn spring-boot:run
```

Interactúa con el menú mostrado en la consola para realizar las operaciones disponibles.

## Cómo funciona

El programa se ejecuta en consola y presenta un menú interactivo donde puedes seleccionar las opciones mencionadas en las funcionalidades.

Los datos de libros y autores se almacenan automáticamente en la base de datos después de las consultas exitosas.

JPA gestiona las operaciones de creación y manipulación de las tablas en la base de datos.

## Estructura del proyecto

```bash
├── src/main/java
│   ├── com.example.literalura
│   │   ├── dto
│   │   │   ├── LibroDTO.java  # DTO para manejar la respuesta de la API sobre libros.
│   │   │   └── AutorDTO.java  # DTO para manejar la respuesta de la API sobre autores.
│   │   ├── modelo
│   │   │   ├── Libro.java  # Entidad libro, representa la tabla o modelo de un libro.
│   │   │   └── Autor.java  # Entidad autor, representa la tabla o modelo de un autor.
│   │   ├── principal
│   │   │   └── Principal.java  # Contiene la lógica principal del menú interactivo de la aplicación.
│   │   ├── repositorio
│   │   │   ├── LibroRepositorio.java  # Repositorio para gestionar operaciones CRUD de libros.
│   │   │   └── AutorRepositorio.java  # Repositorio para gestionar operaciones CRUD de autores.
│   │   └── servicio
│   │       ├── ClienteHTTP.java  # Cliente HTTP para consumir API externa (en este caso, Gutendex).
│   │       ├── ConversorJsonLibro.java  # Conversor para transformar JSON en objetos Java relacionados con libros.
│   │       └── GutendexServicio.java  # Servicio para interactuar con la API Gutendex.
│   └── com.example.literalura.LiteraluraApplication.java  # Punto de entrada de la aplicación Spring Boot.
└── src/main/resources
    ├── application.properties  # Configuración de la conexión a la base de datos y otros parámetros de la aplicación.
```
## Ejemplo de uso

```
===========================================================================
||            Menú de búsqueda de libros en Project Gutenberg            ||
===========================================================================
||                                                                       ||
||   1. Buscar libro por titulo                                          ||
||   2. Listar libros buscados anteriormente                             ||
||   3. Listar libros buscados anteriormente por idioma                  ||
||   4. Listar autores de los libros buscados anterioremente             ||
||   5. Listar autores vivos en un determinado año                       ||
||   6. Cantidad de libros por idioma                                    ||
||                                                                       ||
||   0. Salir                                                            ||
||                                                                       ||
===========================================================================
Elige una opción (0-6): 1

Escriba el título o parte del título del libro que desea buscar: Jekyll
¡Libro encontrado!
//////////////////////////////////////////////////////////////////////////////////////////////////
Título: The Strange Case of Dr. Jekyll and Mr. Hyde
Autor:
    Nombre: Stevenson, Robert Louis
    Nacimiento: 1850
    Fallecimiento: 1894
Idioma: Inglés
Total de Descargas: 32345
//////////////////////////////////////////////////////////////////////////////////////////////////
Hibernate: select l1_0.id_libro,l1_0.id_autor,l1_0.idioma,l1_0.numero_descargas,l1_0.titulo from libros l1_0 where l1_0.titulo=?
Hibernate: select a1_0.id_autor,a1_0.fallecimiento,a1_0.nacimiento,a1_0.nombre from autores a1_0 where a1_0.id_autor=?
 
===========================================================================
||            Menú de búsqueda de libros en Project Gutenberg            ||
===========================================================================
||                                                                       ||
||   1. Buscar libro por titulo                                          ||
||   2. Listar libros buscados anteriormente                             ||
||   3. Listar libros buscados anteriormente por idioma                  ||
||   4. Listar autores de los libros buscados anterioremente             ||
||   5. Listar autores vivos en un determinado año                       ||
||   6. Cantidad de libros por idioma                                    ||
||                                                                       ||
||   0. Salir                                                            ||
||                                                                       ||
===========================================================================
 
Elige una opción (0-6): 
```
