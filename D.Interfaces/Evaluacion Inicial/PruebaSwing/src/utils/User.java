package utils;

public class User {

	private String nombre;
	private String pswd;
	
	public User(String nombre,String pswd) {
		this.nombre = nombre;
		this.pswd = pswd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
}
