package com.example.literalura.modelo;

import com.example.literalura.dto.AutorDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @Column(name = "nacimiento", nullable = true)
    private Integer nacimiento;

    @Column(name = "fallecimiento", nullable = true)
    private Integer fallecimiento;

    public Autor(){}

    public Autor(AutorDTO autorDTO) {
        this.nombre = autorDTO.nombre();
        this.nacimiento = autorDTO.nacimiento();
        this.fallecimiento = autorDTO.fallecimiento();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    @Override
    public String toString() {
        return String.format(
                "Nombre: %s\nNacimiento: %s\nFallecimiento: %s",
                nombre !=  null ? nombre : "N/A",
                nacimiento != null ? String.valueOf(nacimiento) : "N/A",
                fallecimiento != null ? String.valueOf(fallecimiento) : "N/A"
        );
    }
}
