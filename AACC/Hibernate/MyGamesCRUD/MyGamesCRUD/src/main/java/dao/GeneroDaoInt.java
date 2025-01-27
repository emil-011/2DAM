package dao;

import java.util.List;

import models.Genero;


/**
 * Clase interfaz EmpleadoDaoInt
 */
public interface GeneroDaoInt extends CommonDaoInt<Genero> {

	/**
	 * Devuelve los generos que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los generos que queremos obtener de la base de datos
	 * @return Generos que contienen ese nombre
	 */
	public List<Genero> searchByNombre(final String nombre);


	public Genero searchById(final String DNI);

}
