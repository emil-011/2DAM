package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "cod_recuperacion")
	private String codRecuperacion;
	
	public Usuario() {
		
	}
    
	public Usuario(String nombreUsuario, String password, String email, String nombreCompleto, Date fechaNacimiento,
			Date fechaRegistro, String codRecuperacion) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.codRecuperacion = codRecuperacion;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getCodRecuperacion() {
		return codRecuperacion;
	}

	public void setCodRecuperacion(String codRecuperacion) {
		this.codRecuperacion = codRecuperacion;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", email=" + email + "]";
	}
}
