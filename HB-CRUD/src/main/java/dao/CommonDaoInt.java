package dao;

import java.util.List;

public interface CommonDaoInt<T> {

	/**
	 * Inserta un objeto en la base de datos.
	 * 
	 * @param paramT Objeto a insertar.
	 */
	public void insert(final T paramT);

	/**
	 * Actualiza un objeto de la base de datos.
	 * 
	 * @param paramT Objeto a actualizar.
	 */
	public void update(final T paramT);

	/**
	 * Elimina un objeto de la base de datos.
	 * 
	 * @param paramT Objeto a eliminar.
	 */
	public void delete(final T paramT);

	/**
	 * Devuelve todos los objetos de la base de datos.
	 * 
	 * @return Lista de objetos.
	 */
	public List<T> searchAll();
}
