package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import models.Usuario;
import models.Genero;

public class UsuarioDaoImpl extends CommonDaoImpl<Usuario> implements UsuarioDaoInt {
	// Session de la base de datos
	private Session session;

	/**
	 * Constructor Empleado DAO
	 * 
	 * @param session Session de la base de datos
	 */
	public UsuarioDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Devuelve los empleados que tienen ese nombre en la base de datos
	 * 
	 * @param name Nombre de los empleados
	 * @return Empleados con ese nombre
	 */
	public List<Usuario> searchByNombre(final String nombre) {
		// Verificación de sesión abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve los usuarios con ese nombre, evitando la inyección de SQL
		return session.createQuery("FROM Usuario WHERE nombre = :nombre", Usuario.class).setParameter("nombre", nombre)
				.getResultList();
	}

	/**
	 * Devuelve el empleado que tenga el DNI informado
	 */
	public Usuario searchById(int Id) {
		// Verificación de sesión abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Devuelve el usuario con el DNI proporcionado, evitando la inyección SQL
		return (Usuario) session.createQuery("FROM Usuario WHERE id_usuario = :Id", Usuario.class)
				.setParameter("id_usuario", Id).uniqueResult();
	}

	/**
	 * Devuelve los géneros favoritos de un usuario dado su id.
	 * 
	 * @param idUsuario El id del usuario
	 * @return Lista de géneros favoritos del usuario
	 */
	public List<Genero> searchGenerosFavoritos(int idUsuario) {
	    // Verificación de sesión abierta
	    if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
	        session.getTransaction().begin();
	    }

	    // Consulta para obtener los géneros favoritos del usuario
	    String hql = "SELECT g FROM Usuario u JOIN u.generosFavoritos g WHERE u.idUsuario = :idUsuario";
	    List<Genero> generosFavoritos = session.createQuery(hql, Genero.class)
	                                            .setParameter("idUsuario", idUsuario)
	                                            .getResultList();

	    // Retorna los géneros favoritos del usuario
	    return generosFavoritos.isEmpty() ? null : generosFavoritos;
	}


	/**
	 * Asocia géneros favoritos a un usuario.
	 * 
	 * @param idUsuario El id del usuario
	 * @param generos   Lista de géneros a asociar al usuario
	 */
	public void setGenerosFavoritos(int idUsuario, List<Genero> generos) {
		// Verificación de sesión abierta
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		// Obtener usuario por id
		Usuario usuario = session.get(Usuario.class, idUsuario);
		if (usuario != null) {
			// Asignar los géneros favoritos al usuario
			usuario.setGenerosFavoritos(generos);
			session.merge(usuario); // Guardar los cambios en la base de datos
		}
	}

}
