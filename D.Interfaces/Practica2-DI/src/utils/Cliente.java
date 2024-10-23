package utils;

public class Cliente {

	private String nombre;
	private String apellido;
	private int edad;
	private String provincia;
	private String email;

	public Cliente(String nombre, String apellido, int edad, String provincia, String email) {
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

	@Override
	public String toString() {
		return "👤 Cliente: " + nombre.toUpperCase() + " " + apellido.toUpperCase() + " | Edad: " + edad
				+ " años | Provincia: " + provincia + " | Email: " + email;
	}

}
