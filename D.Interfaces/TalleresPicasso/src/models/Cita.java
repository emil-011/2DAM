package models;

import java.util.Date;

public class Cita {
    private String matricula;
    private String marca;
    private String modelo;
    private Date fecha;
    private String estadoReparacion;
    private double importe;
    private String observaciones;

    // Constructor actualizado
    public Cita(String matricula, String marca, String modelo, Date fecha, String estadoReparacion, double importe, String observaciones) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
        this.estadoReparacion = estadoReparacion;
        this.importe = importe;
        this.observaciones = observaciones;
    }
    
	// Getters y setters para la marca
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Otros getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(String estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
