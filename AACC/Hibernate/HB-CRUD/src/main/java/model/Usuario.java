package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements java.io.Serializable {

    @Id
    @Column(length = 10)
    private String id;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "cod_recuperacion", length = 50)
    private String codRecuperacion;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Biblioteca> bibliotecas = new HashSet<>();


    public Usuario() {
    	
    }


	public Usuario(String id, String nombreUsuario, String password, String email, String nombreCompleto,
			Date fechaNacimiento, Date fechaRegistro, String codRecuperacion, Set<Biblioteca> bibliotecas) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.codRecuperacion = codRecuperacion;
		this.bibliotecas = bibliotecas;
	}
    
    
}
