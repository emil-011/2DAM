package models;

import jakarta.persistence.*;

@Entity
@Table(name = "generos_favoritos")
public class GenerosFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero_favorito")
    private int idGeneroFavorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

	/**
	 * @return the idGeneroFavorito
	 */
	public int getIdGeneroFavorito() {
		return idGeneroFavorito;
	}

	/**
	 * @param idGeneroFavorito the idGeneroFavorito to set
	 */
	public void setIdGeneroFavorito(int idGeneroFavorito) {
		this.idGeneroFavorito = idGeneroFavorito;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

    // Getters y Setters
}
