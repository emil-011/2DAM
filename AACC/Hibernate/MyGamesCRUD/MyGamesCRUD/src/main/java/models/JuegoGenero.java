package models;

import jakarta.persistence.*;

@Entity
@Table(name = "juego_genero")
public class JuegoGenero {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_juego", referencedColumnName = "id_juego")
    private Juego juego;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    // Getters y setters
    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
