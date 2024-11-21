package models;

import java.util.List;

import jakarta.persistence.OneToMany;

public class Titular {
    private String dniTitular;
    private String nombre;
    private String domicilio;
    private String codRest;
    
    // Uno a muchos de titular
    @OneToMany(mappedBy = "titular")
    private List<Restaurante> listaRestaurantes;

    // Constructor
    public Titular(String dniTitular, String nombre, String domicilio, String codRest) {
        this.dniTitular = dniTitular;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.codRest = codRest;
    }

    // Getters and Setters
    public String getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(String dniTitular) {
        this.dniTitular = dniTitular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }
}

