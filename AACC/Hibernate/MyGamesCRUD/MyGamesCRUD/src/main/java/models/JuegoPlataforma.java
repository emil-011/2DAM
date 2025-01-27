package models;

import jakarta.persistence.*;

@Entity
@Table(name = "juego_plataforma")
public class JuegoPlataforma {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_juego", referencedColumnName = "id_juego")
    private Juego juego;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_plataforma", referencedColumnName = "id_plataforma")
    private Plataforma plataforma;

    // Getters y setters
    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
}
