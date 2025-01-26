package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "juego_id")
	private Juegos juego;

	@Column(name = "estado")
	private String estado;

	@Column(name = "es_favorito")
	private boolean esFavorito;

	@Column(name = "es_deseado")
	private boolean esDeseado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_adicion")
	private Date fechaAdicion;

	public Biblioteca() {
	}

	public Biblioteca(Usuario usuario, Juegos juego, String estado, boolean esFavorito, boolean esDeseado,
			Date fechaAdicion) {
		this.usuario = usuario;
		this.juego = juego;
		this.estado = estado;
		this.esFavorito = esFavorito;
		this.esDeseado = esDeseado;
		this.fechaAdicion = fechaAdicion;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Juegos getJuego() {
		return juego;
	}

	public void setJuego(Juegos juego) {
		this.juego = juego;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isEsFavorito() {
		return esFavorito;
	}

	public void setEsFavorito(boolean esFavorito) {
		this.esFavorito = esFavorito;
	}

	public boolean isEsDeseado() {
		return esDeseado;
	}

	public void setEsDeseado(boolean esDeseado) {
		this.esDeseado = esDeseado;
	}

	public Date getFechaAdicion() {
		return fechaAdicion;
	}

	public void setFechaAdicion(Date fechaAdicion) {
		this.fechaAdicion = fechaAdicion;
	}

	@Override
	public String toString() {
		return "Biblioteca [usuario=" + usuario.getNombreUsuario() + ", juego=" + juego.getTitulo() + ", estado="
				+ estado + "]";
	}
}
