package models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private int idGenero;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @ManyToMany(mappedBy = "generosFavoritos")
    private Set<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "genero")
    private Set<UsuarioGeneroFavorito> usuarioGenerosFavoritos;

    // Getters y Setters
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuario> getUsuariosFavoritos() {
        return usuariosFavoritos;
    }

    public void setUsuariosFavoritos(Set<Usuario> usuariosFavoritos) {
        this.usuariosFavoritos = usuariosFavoritos;
    }

    public Set<UsuarioGeneroFavorito> getUsuarioGenerosFavoritos() {
        return usuarioGenerosFavoritos;
    }

    public void setUsuarioGenerosFavoritos(Set<UsuarioGeneroFavorito> usuarioGenerosFavoritos) {
        this.usuarioGenerosFavoritos = usuarioGenerosFavoritos;
    }
}
