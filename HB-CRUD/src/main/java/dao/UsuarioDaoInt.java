package dao;

import model.Usuario;

public interface UsuarioDaoInt extends CommonDaoInt<Usuario> {

	/**
	 * Busca un usuario por su nombre de usuario.
	 * 
	 * @param nombreUsuario - Nombre de usuario.
	 * @return - El usuario con el nombre de usuario especificado.
	 */
	Usuario searchByNombreUsuario(String nombreUsuario);

	/**
	 * Busca un usuario por su correo electrónico.
	 * 
	 * @param email - Correo electrónico del usuario.
	 * @return - El usuario con el correo electrónico especificado.
	 */
	Usuario searchByEmail(String email);

	/**
	 * Busca un usuario por su ID.
	 * 
	 * @param id - ID del usuario.
	 * @return - El usuario con el ID especificado.
	 */
	Usuario searchById(int id);
}
