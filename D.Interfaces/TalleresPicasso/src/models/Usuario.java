package models;

import java.util.List;

public class Usuario {
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private String contrasenya;
	private boolean esEmpleado;
	private List<Cita> listaCitas;

	public Usuario(String nombre, String apellidos, String telefono, String email, String contrasenya,
			boolean esEmpleado, List<Cita> listaCitas) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.contrasenya = contrasenya;
		this.esEmpleado = esEmpleado;
		this.listaCitas = listaCitas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public boolean isEsEmpleado() {
		return esEmpleado;
	}

	public void setEsEmpleado(boolean esEmpleado) {
		this.esEmpleado = esEmpleado;
	}

	public List<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(List<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}

}
