package dao;

import model.Biblioteca;
import java.util.List;

public interface BibliotecaDaoInt extends CommonDaoInt<Biblioteca> {

	/**
	 * Busca una biblioteca por el ID del usuario.
	 * 
	 * @param usuarioId - ID del usuario.
	 * @return - La lista de juegos en la biblioteca de un usuario.
	 */
	List<Biblioteca> searchByUserId(int usuarioId);

	/**
	 * Busca los juegos de un usuario que están en su lista de favoritos.
	 * 
	 * @param usuarioId - ID del usuario.
	 * @return - Lista de juegos favoritos en la biblioteca de un usuario.
	 */
	List<Biblioteca> searchFavourites(int usuarioId);

	/**
	 * Busca los juegos de un usuario que están en su lista de deseados.
	 * 
	 * @param usuarioId - ID del usuario.
	 * @return - Lista de juegos deseados en la biblioteca de un usuario.
	 */
	List<Biblioteca> searchWish(int usuarioId);
}
