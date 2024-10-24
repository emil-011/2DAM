package utils;

/**
 * Clase cliente
 */
public class Clientes {

	private String nombre;
	private String apellido;
	private int edad;
	private String provincia;
	private String email;

	/**
	 * Construcor cliente
	 * @param nombre Nombre del cliente
	 * @param apellido Apellido del cliente
	 * @param edad Edad del cliente
	 * @param provincia Provincia donde vive el cliente
	 * @param email Email del cliente
	 */
	public Clientes(String nombre, String apellido, int edad, String provincia, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.provincia = provincia;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getEdad() {
		return edad;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getEmail() {
		return email;
	}

}
