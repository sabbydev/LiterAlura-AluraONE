package com.example.literalura.principal;

import com.example.literalura.dto.LibroDTO;
import com.example.literalura.modelo.Autor;
import com.example.literalura.modelo.Libro;
import com.example.literalura.repositorio.LibroRepositorio;
import com.example.literalura.repositorio.AutorRepositorio;
import com.example.literalura.servicio.GutendexServicio;

import java.util.*;

public class Principal {
    private static final Scanner escaner = new Scanner(System.in);
    private final LibroRepositorio libroRepositorio;
    private final AutorRepositorio autorRepositorio;

    public Principal(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void ejecutar(){
        int opcionMenu;
        while(true) {
            System.out.print("""
                \s
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
                \s
                Elige una opción (0-6):\s""");
            opcionMenu = escanearOpcion();
            switch (opcionMenu) {
                case 0 -> {System.out.println("¡Hasta luego!");escaner.close();System.exit(0);}
                case 1 -> buscarPrimerLibroPorTitulo();
                case 2 -> listarLibrosBuscadosAnteriormente();
                case 3 -> buscarPrimerLibroConAlMenosUnoDeLosIdiomasSeleccionados();
                case 4 -> listarAutoresDeLibrosBuscadosAnteriormente();
                case 5 -> listarAutoresVivosEnA();
                case 6 -> cantidadLibrosPorIdioma();
                default -> System.out.println("Opción inválida. Por favor, elige una opción entre 1 y 6.");
            }
        }
    }

    private void cantidadLibrosPorIdioma() {
        String codigoSeleccionado;

        while(true){
            System.out.print("""
                Seleccione un idioma. Ingrese '0' para finalizar.
                \s
                1. Inglés (en)
                2. Francés (fr)
                3. Finlandés (fi)
                4. Español (es)
                5. Alemán (de)
                6. Italiano (it)
                7. Portugués (pt)
                8. Ruso (ru)
                9. Chino (zh)
                10. Árabe (ar)
                \s
                0. Regresar
                \s
                Ingrese el número del idioma deseado:\s""");

            int seleccion = escanearOpcion();

            switch (seleccion) {
                case 0 -> {return;}
                case 1 -> codigoSeleccionado = "en";
                case 2 -> codigoSeleccionado = "fr";
                case 3 -> codigoSeleccionado = "fi";
                case 4 -> codigoSeleccionado = "es";
                case 5 -> codigoSeleccionado = "de";
                case 6 -> codigoSeleccionado = "it";
                case 7 -> codigoSeleccionado = "pt";
                case 8 -> codigoSeleccionado = "ru";
                case 9 -> codigoSeleccionado = "zh";
                case 10 -> codigoSeleccionado = "ar";
                default -> {
                    System.out.println("Selección no válida. Intente de nuevo.");
                    continue;
                }
            }
            break;
        }

        System.out.println("Idioma seleccionado: " + traducirCodigoIdioma(codigoSeleccionado));
        System.out.println();

        Long cantidad = libroRepositorio.contarLibrosPorIdioma(codigoSeleccionado);

        if (cantidad == 0) {
            System.out.println("No hay libros registrados para el idioma: " + traducirCodigoIdioma(codigoSeleccionado));
        } else {
            System.out.println("Cantidad de libros en " + traducirCodigoIdioma(codigoSeleccionado) + ": " + cantidad + " libro(s)");
        }
    }

    public void listarAutoresVivosEnA() {
        System.out.print("Ingrese el año: ");
        int a = escanearOpcion();

        List<Autor> autoresVivosEnA = autorRepositorio.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqualOrFallecimientoIsNull(a, a);

        if (autoresVivosEnA.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + a + ".");
        } else {
            System.out.println("Autores vivos en el año " + a + ":");
            autoresVivosEnA.forEach(this::imprimir);
        }
    }

    private void listarAutoresDeLibrosBuscadosAnteriormente() {
        List<Autor> respuesta = autorRepositorio.findAll();
        if (respuesta.isEmpty()) {
            System.out.println("No se han encontrado autores de libros buscados anteriormente.");
        } else {
            System.out.println("Listado de autores de libros guardados previamente:");
            respuesta.forEach(this::imprimir);
        }
    }

    private void buscarPrimerLibroConAlMenosUnoDeLosIdiomasSeleccionados() {
        String codigoSeleccionado;

        while(true){
            System.out.print("""
                Seleccione un idioma. Ingrese '0' para finalizar.
                \s
                1. Inglés (en)
                2. Francés (fr)
                3. Finlandés (fi)
                4. Español (es)
                5. Alemán (de)
                6. Italiano (it)
                7. Portugués (pt)
                8. Ruso (ru)
                9. Chino (zh)
                10. Árabe (ar)
                \s
                0. Regresar
                \s
                Ingrese el número del idioma deseado:\s""");

            int seleccion = escanearOpcion();

            switch (seleccion) {
                case 0 -> {return;}
                case 1 -> codigoSeleccionado = "en";
                case 2 -> codigoSeleccionado = "fr";
                case 3 -> codigoSeleccionado = "fi";
                case 4 -> codigoSeleccionado = "es";
                case 5 -> codigoSeleccionado = "de";
                case 6 -> codigoSeleccionado = "it";
                case 7 -> codigoSeleccionado = "pt";
                case 8 -> codigoSeleccionado = "ru";
                case 9 -> codigoSeleccionado = "zh";
                case 10 -> codigoSeleccionado = "ar";
                default -> {
                    System.out.println("Selección no válida. Intente de nuevo.");
                    continue;
                }
            }
            break;
        }

        System.out.println("Idioma seleccionado: " + traducirCodigoIdioma(codigoSeleccionado));
        System.out.println();

        List<Libro> respuesta = libroRepositorio.findByIdioma(codigoSeleccionado);

        if (respuesta.isEmpty()) {
            System.out.println("No se encontró ningún libro en el idioma seleccionado.");
        } else {
            System.out.println("Libros encontrados en idioma " + traducirCodigoIdioma(codigoSeleccionado) + ":");
            respuesta.forEach(this::imprimir);
        }
    }

    private void listarLibrosBuscadosAnteriormente() {
        List<Libro> respuesta = libroRepositorio.findAll();
        if (respuesta.isEmpty()) {
            System.out.println("No se han encontrado libros buscados anteriormente.");
        } else {
            System.out.println("Listado de libros guardados previamente:");
            respuesta.forEach(this::imprimir);
        }
    }

    private void buscarPrimerLibroPorTitulo() {
        System.out.print("Escriba el título o parte del título del libro que desea buscar: ");
        String titulo = escaner.nextLine().trim();
        String consulta = titulo.replace(" ", "%20");

        if (consulta.isEmpty()) {
            System.out.println("Búsqueda vacía. Por favor, ingrese un título válido.");
            return;
        }

        Optional<LibroDTO> respuesta = GutendexServicio.consultarCoincidenciasTituloAutor(consulta);

        if (respuesta.isPresent()) {
            LibroDTO libroDTO = respuesta.get();
            System.out.println("¡Libro encontrado!");
            imprimir(libroDTO);
            List<Libro> libroExistente = libroRepositorio.findByTitulo(libroDTO.titulo());
            if(libroExistente.isEmpty()) {
                Libro libro = new Libro(libroDTO);
                libroRepositorio.save(libro);
                System.out.println("Libro guardado con éxito: " + libro);
            }
        } else {
            System.out.println("No se encontró un libro con ese título.");
        }
    }

    private int escanearOpcion(){
        int opcion;
        try {
            opcion = escaner.nextInt();
            System.out.println(escaner.nextLine());
            return opcion;
        } catch (InputMismatchException e) {
            escaner.nextLine();
            return -1;
        }
    }

    private <T> void imprimir(T objeto) {
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(objeto);
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////");
    }

    private static final Map<String, String> idiomaMap = Map.of(
            "en", "Inglés",
            "fr", "Francés",
            "es", "Español",
            "de", "Alemán",
            "it", "Italiano",
            "pt", "Portugués",
            "ru", "Ruso",
            "zh", "Chino",
            "ar", "Árabe"
    );

    private String traducirCodigoIdioma(String codigo) {
        return idiomaMap.getOrDefault(codigo, codigo);
    }
}