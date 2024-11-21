package models;

import java.util.List;

import jakarta.persistence.OneToMany;

public class Localidad {
    private int codLocalidad;
    private String nombre;
    
    @OneToMany(mappedBy = "localidad")
    private List<Restaurante> listaRestaurantes;

    // Constructor
    public Localidad(int codLocalidad, String nombre) {
        this.codLocalidad = codLocalidad;
        this.nombre = nombre;
    }

    // Getters and Setters
    public int getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(int codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
