package com.example.literalura.modelo;

import com.example.literalura.dto.LibroDTO;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_libro", nullable = false)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @Column(name="idioma", nullable = false)
    private String idioma;

    @Column(name="numero_descargas", nullable = true)
    private Long numeroDescargas;

    public Libro(){}

    public Libro(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        this.autor = new Autor(libroDTO.autor());
        this.idioma = libroDTO.idioma();
        this.numeroDescargas = libroDTO.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

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
