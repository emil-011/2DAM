package models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "juegos")
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_juego")
	private int idJuego;

	@Column(name = "titulo", nullable = false, length = 100)
	private String titulo;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "fecha_lanzamiento")
	@Temporal(TemporalType.DATE)
	private Date fechaLanzamiento;

	@ManyToOne
	@JoinColumn(name = "id_desarrollador", nullable = false)
	private Desarollador desarollador;

	@ManyToOne
	@JoinColumn(name = "id_plataforma", nullable = false)
	private Plataforma plataforma;

	@Column(name = "creado_por_usuario", nullable = false)
	private boolean creadoPorUsuario;

	@Column(name = "imagenes", columnDefinition = "TEXT")
	private String imagenes;

	@Column(name = "jugado", nullable = false, columnDefinition = "TINYINT")
	private int jugado;

	@ManyToOne
	@JoinColumn(name = "id_wishlist", nullable = true) // Relación con Wishlist
	private Wishlist wishlist;

	@ManyToMany
	@JoinTable(
	    name = "juego_genero",
	    joinColumns = @JoinColumn(name = "id_juego"),
	    inverseJoinColumns = @JoinColumn(name = "id_genero")
	)
	private List<Genero> generos;

	// Getters y Setters

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Desarollador getDesarrollador() {
		return desarollador;
	}

	public void setDesarrollador(Desarollador desarollador) {
		this.desarollador = desarollador;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public boolean isCreadoPorUsuario() {
		return creadoPorUsuario;
	}

	public void setCreadoPorUsuario(boolean creadoPorUsuario) {
		this.creadoPorUsuario = creadoPorUsuario;
	}

	public String getImagenes() {
		return imagenes;
	}

	public void addGeneros(List<Genero> generos) {
    this.generos = generos;
}

	
	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}

	public int getJugado() {
		return jugado;
	}

	public void setJugado(int jugado) {
		this.jugado = jugado;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	
	
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	// Añadir toString para imprimir información útil
	@Override
	public String toString() {
		return "Juego [idJuego=" + idJuego + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaLanzamiento="
				+ fechaLanzamiento + ", desarollador=" + desarollador + ", plataforma=" + plataforma
				+ ", creadoPorUsuario=" + creadoPorUsuario + ", imagenes=" + imagenes + ", jugado=" + jugado
				+ ", wishlist=" + wishlist + ", generos=" + generos + "]";
	}

	// Añadir equals y hashCode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Juego other = (Juego) obj;
		return idJuego == other.idJuego;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(idJuego);
	}
}
