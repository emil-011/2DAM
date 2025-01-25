package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Biblioteca implements java.io.Serializable {

	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@MapsId("juegoId")
	@JoinColumn(name = "juego_id", nullable = false)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private String estado;

	@Column(nullable = false)
	private boolean favorito;

	@Column(nullable = false)
	private boolean deseado;

	@Column(name = "fecha_added")
	private Date fechaAdded;

	public Biblioteca() {
	}

	public Biblioteca(Usuario usuario, int id, String estado, boolean favorito, boolean deseado, Date fechaAdded) {
		super();
		this.usuario = usuario;
		this.id = id;
		this.estado = estado;
		this.favorito = favorito;
		this.deseado = deseado;
		this.fechaAdded = fechaAdded;
	}

}