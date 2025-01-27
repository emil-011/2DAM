package models;


import jakarta.persistence.*;

@Entity
@Table(name = "desarrolladores")
public class Desarollador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desarrollador")
    private int idDesarrollador;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "pais", length = 50)
    private String pais;

	/**
	 * @return the idDesarrollador
	 */
	public int getIdDesarrollador() {
		return idDesarrollador;
	}

	/**
	 * @param idDesarrollador the idDesarrollador to set
	 */
	public void setIdDesarrollador(int idDesarrollador) {
		this.idDesarrollador = idDesarrollador;
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

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

    // Getters y Setters
}
