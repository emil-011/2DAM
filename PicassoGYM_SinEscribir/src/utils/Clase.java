package utils;

import java.util.Objects;

public class Clase {
    private String nombre;
    private String profesor;
    private String turno;

    public Clase(String nombre, String profesor, String turno) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getTurno() {
        return turno;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Clase)) return false;
        Clase clase = (Clase) obj;
        return nombre.equals(clase.nombre) && turno.equals(clase.turno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, turno);
    }
}
