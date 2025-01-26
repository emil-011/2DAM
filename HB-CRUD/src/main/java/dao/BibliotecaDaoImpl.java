package dao;

import model.Biblioteca;
import org.hibernate.Session;
import java.util.List;

public class BibliotecaDaoImpl extends CommonDaoImpl<Biblioteca> implements BibliotecaDaoInt {

	public BibliotecaDaoImpl(Session session) {
		super(session);
	}

	
	@Override
	public List<Biblioteca> searchByUserId(int usuarioId) {
		return getSession().createQuery("FROM Biblioteca b WHERE b.usuarioId.id = :usuarioId", Biblioteca.class)
				.setParameter("usuarioId", usuarioId).getResultList();
	}

	@Override
	public List<Biblioteca> searchFavourites(int usuarioId) {
		return getSession().createQuery("FROM Biblioteca b WHERE b.usuarioId.id = :usuarioId AND b.favorito = true",
				Biblioteca.class).setParameter("usuarioId", usuarioId).getResultList();
	}

	@Override
	public List<Biblioteca> searchWish(int usuarioId) {
		return getSession().createQuery("FROM Biblioteca b WHERE b.usuarioId.id = :usuarioId AND b.deseado = true",
				Biblioteca.class).setParameter("usuarioId", usuarioId).getResultList();
	}
}
