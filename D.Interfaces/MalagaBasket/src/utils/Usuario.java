package utils;

import java.util.Date;

public class Usuario {

    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String email;
    private String contrasenya;
    private boolean esEntrenador;

    public Usuario(String nombre, String apellidos, Date fechaNacimiento, String email,
                   String contrasenya, boolean esEntrenador) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenya = contrasenya;
        this.esEntrenador = esEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public boolean isEntrenador() {
        return esEntrenador;
    }


}
