package utils;

import java.util.List;

public class Equipo {

	private String nombre;
	private int anyo;
	private String genero;
	private String horarioEntreno;
	private List<Usuario> listaJugadores;
	private Usuario entrenador;

	public Equipo(String nombre, int anyo, String genero, String horarioEntreno, List<Usuario> listaJugadores,
			Usuario entrenador) {
		super();
		this.nombre = nombre;
		this.anyo = anyo;
		this.genero = genero;
		this.horarioEntreno = horarioEntreno;
		this.listaJugadores = listaJugadores;
		this.entrenador = entrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getHorarioEntreno() {
		return horarioEntreno;
	}

	public void setHorarioEntreno(String horarioEntreno) {
		this.horarioEntreno = horarioEntreno;
	}

	public List<Usuario> getListaJugadores() {
		return listaJugadores;
	}

	public void setListaJugadores(List<Usuario> listaJugadores) {
		this.listaJugadores = listaJugadores;
	}

	public Usuario getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Usuario entrenador) {
		this.entrenador = entrenador;
	}

}
