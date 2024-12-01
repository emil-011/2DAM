package utils;

/**
 * Clase Profesor
 */
public class Profesor {

	// Atributos
	private String nif;
	private String nombre;
	private String especialidad;
	private String telefono;
	
	/**
	 * Constructor para profesor
	 * @param nif Nif del profesor
	 * @param nombre Nombre del profesor
	 * @param especialidad Especialidad del profesor
	 * @param telefono Telefono del profesor
	 */
	public Profesor(String nif, String nombre, String especialidad, String telefono) {
		this.nif = nif;
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.telefono = telefono;
	}

	public String getNif() {
		return nif;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public String getTelefono() {
		return telefono;
	}
	
	
	
	
}
