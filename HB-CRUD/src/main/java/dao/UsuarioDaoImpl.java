package dao;

import model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuarioDaoImpl extends CommonDaoImpl<Usuario> implements UsuarioDaoInt {

	public UsuarioDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Usuario searchByNombreUsuario(String nombreUsuario) {
		Query<Usuario> query = getSession().createQuery("FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario",
				Usuario.class);
		query.setParameter("nombreUsuario", nombreUsuario);
		return query.uniqueResult();
	}

	@Override
	public Usuario searchByEmail(String email) {
		Query<Usuario> query = getSession().createQuery("FROM Usuario u WHERE u.email = :email", Usuario.class);
		query.setParameter("email", email);
		return query.uniqueResult();
	}

	@Override
	public Usuario searchById(int id) {
		return getSession().get(Usuario.class, id);
	}
}
