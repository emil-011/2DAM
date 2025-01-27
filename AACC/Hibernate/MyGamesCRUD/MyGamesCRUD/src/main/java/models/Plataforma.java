package models;

import jakarta.persistence.*;

@Entity
@Table(name = "plataformas")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plataforma")
    private int idPlataforma;

    @ManyToOne
    @JoinColumn(name = "id_grupo", nullable = false)
    private GrupoPlataformas grupoPlataforma;

    /**
	 * @return the idPlataforma
	 */
	public int getIdPlataforma() {
		return idPlataforma;
	}

	/**
	 * @param idPlataforma the idPlataforma to set
	 */
	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	/**
	 * @return the grupoPlataforma
	 */
	public GrupoPlataformas getGrupoPlataforma() {
		return grupoPlataforma;
	}

	/**
	 * @param grupoPlataforma the grupoPlataforma to set
	 */
	public void setGrupoPlataforma(GrupoPlataformas grupoPlataforma) {
		this.grupoPlataforma = grupoPlataforma;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // Getters y Setters
}
