package com.example.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") AutorDTO autor,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Long numeroDescargas
) {
    @Override
    public String toString() {
        return String.format(
                "Título: %s\n" +
                        "Autor:\n%s\n" +
                        "Idioma: %s\n" +
                        "Total de Descargas: %s",
                titulo != null ? titulo : "N/A",
                autor != null ? "    " + autor.toString().replace("\n", "\n    ") : "N/A",
                traducirCodigoIdioma(idioma != null ? idioma : "N/A"),
                numeroDescargas != null ? String.valueOf(numeroDescargas) : "N/A"
        );
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