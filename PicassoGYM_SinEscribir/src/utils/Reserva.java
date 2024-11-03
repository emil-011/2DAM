package utils;

public class Reserva {
    private String nombre;
    private String apellidos;
    private String clase;
    private String turno;

    public Reserva(String nombre, String apellidos, String clase, String turno) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clase = clase;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getClase() {
        return clase;
    }

    public String getTurno() {
        return turno;
    }
}
