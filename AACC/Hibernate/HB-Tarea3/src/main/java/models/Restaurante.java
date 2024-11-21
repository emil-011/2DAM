package models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class Restaurante {
    private String codRest;
    private String nombre;
    private String licenciaFiscal;
    private String domicilio;
    private Date fechaApertura;
    private String horario;
    private int codLocalidad;
    
    // Relación muchos a uno
    @ManyToOne
    @JoinColumn(name = "cod_rest")
    private Titular titular;
    
    @OneToOne
    @JoinColumn(name = "cod_localidad")
    private Localidad localidad;
    
    @OneToMany(mappedBy = "restaurante")
    private List<Existencias> listaExistencias;
    
    // Constructor
    public Restaurante() {
    }

    // Constructor
    public Restaurante(String codRest, String nombre, String licenciaFiscal, String domicilio, 
                       Date fechaApertura, String horario, int codLocalidad) {
        this.codRest = codRest;
        this.nombre = nombre;
        this.licenciaFiscal = licenciaFiscal;
        this.domicilio = domicilio;
        this.fechaApertura = fechaApertura;
        this.horario = horario;
        this.codLocalidad = codLocalidad;
    }

    // Getters and Setters
    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicenciaFiscal() {
        return licenciaFiscal;
    }

    public void setLicenciaFiscal(String licenciaFiscal) {
        this.licenciaFiscal = licenciaFiscal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(int codLocalidad) {
        this.codLocalidad = codLocalidad;
    }
}
