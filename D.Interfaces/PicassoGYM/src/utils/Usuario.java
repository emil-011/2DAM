package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String perfil;
	private String email;
	private char[] contrasenya;
	private boolean isLogged;

	public Usuario(String nombre, String apellidos, Date fechaNacimiento, String perfil, String email,
			char[] contrasenya, boolean isLogged) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.perfil = perfil;
		this.email = email;
		this.contrasenya = contrasenya;
		this.isLogged = isLogged;

	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getPerfil() {
		return perfil;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasenya() {	
		return new String(contrasenya);
	}

	public boolean isLogged() {
		return isLogged;
	}
	
	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	    String fechaFormateada = sdf.format(fechaNacimiento);
	    return nombre + ";" +
	           apellidos + ";" +
	           fechaFormateada + ";" +
	           perfil + ";" +
	           email + ";" +
	           getContrasenya() + ";" +
	           isLogged;
	}
	

}