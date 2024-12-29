package com.example.literalura.servicio;

import com.example.literalura.dto.LibroDTO;

import java.util.Optional;

public class GutendexServicio {
    private static final String URL_BASE = "https://gutendex.com/books/";

    public static Optional<LibroDTO> consultarLibrosPorAutoresVivosDesde(int inicio) {
        return consultar("?author_year_start=" + inicio);
    }

    public static Optional<LibroDTO> consultarLibrosPorAutoresVivosAntes(int fin) {
        return consultar("?author_year_end=" + fin);
    }

    public static Optional<LibroDTO> consultarLibrosPorAutoresVivosEntre(int inicio, int fin) {
        return consultar("?author_year_start=" + inicio + "&author_year_end=" + fin);
    }

    public static Optional<LibroDTO> consultarLibrosConDerechosDeAutor() {
        return consultar("?copyright=true");
    }

    public static Optional<LibroDTO> consultarLibrosSinDerechosDeAutor() {
        return consultar("?copyright=false");
    }

    public static Optional<LibroDTO> consultarLibrosConYSinDerechosDeAutor() {
        return consultar("?copyright=true,false");
    }

    public static Optional<LibroDTO> consultarLibrosPorId(String parametros) {
        return consultar("?ids=" + parametros);
    }

    public static Optional<LibroDTO> consultarLibrosPorIdioma(String parametros) {
        return consultar("?languages=" + parametros);
    }

    public static Optional<LibroDTO> consultarLibrosPorTipoFormato(String parametro) {
        return consultar("?mime_type=" + parametro);
    }

    public static Optional<LibroDTO> consultarCoincidenciasTituloAutor(String busqueda) {
        return consultar("?search=" + busqueda);
    }

    public static Optional<LibroDTO> consultarLibrosOrdenadosPorIdAscendente() {
        return consultar("?sort=ascending");
    }

    public static Optional<LibroDTO> consultarLibrosOrdenadosPorIdDescendente() {
        return consultar("?sort=descending");
    }

    public static Optional<LibroDTO> consultarLibrosOrdenadosPorTotalDescargasDescendente() {
        return consultar("?sort=popular");
    }

    public static Optional<LibroDTO> consultarCoincidenciasCategoriaTema(String busqueda) {
        return consultar("?topic=" + busqueda);
    }

    public static Optional<LibroDTO> consultarLibroPorId(long id) {
        return consultar(String.valueOf(id) + "/");
    }

    private static Optional<LibroDTO> consultar(String consulta) {
        return ConversorJsonLibro.JsonALibroDTO(ClienteHTTP.obtenerRespuesta(URL_BASE + consulta));
    }
}