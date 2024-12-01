package utils;

/**
 * Clase asignatura
 */
public class Asignatura {
	
	private String codAsignatura;
	private String nombre;
	
	/**
	 * Constructor Asignatura
	 * @param codAsignatura Codigo de la asignatura
	 * @param nombre Nombre de la asignatura
	 */
	public Asignatura(String codAsignatura, String nombre) {
		this.codAsignatura = codAsignatura;
		this.nombre = nombre;
	}


	public String getCodAsignatura() {
		return codAsignatura;
	}


	public String getNombre() {
		return nombre;
	}
	
}
