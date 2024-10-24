package utils;

public class Cliente {

	private String nombre;
	private String apellido;
	private int edad;
	private String provincia;

	public Cliente(String nombre, String apellido, int edad, String provincia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.provincia = provincia;
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


}
