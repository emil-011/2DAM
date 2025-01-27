package dao;

import models.Juego;
import models.Genero;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Date;

public class JuegoDaoImpl extends CommonDaoImpl<Juego> implements JuegoDaoInt {

	private final Session session;

	/**
	 * Constructor de JuegoDaoImpl.
	 *
	 * @param session La sesión de Hibernate para interactuar con la base de datos.
	 */
	public JuegoDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Juego> searchByTitulo(String titulo) {
		String hql = "FROM Juego j WHERE LOWER(j.titulo) LIKE LOWER(:titulo)";
		Query<Juego> query = session.createQuery(hql, Juego.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList();
	}

	@Override
	public List<Juego> searchByFechaLanzamiento(Date fecha) {
		String hql = "FROM Juego j WHERE j.fechaLanzamiento > :fecha";
		Query<Juego> query = session.createQuery(hql, Juego.class);
		query.setParameter("fecha", fecha);
		return query.getResultList();
	}

	@Override
	public List<Juego> searchByDesarrollador(int idDesarrollador) {
		String hql = "FROM Juego j WHERE j.desarrollador.idDesarrollador = :idDesarrollador";
		Query<Juego> query = session.createQuery(hql, Juego.class);
		query.setParameter("idDesarrollador", idDesarrollador);
		return query.getResultList();
	}

	@Override
	public List<Juego> searchByPlataforma(int idPlataforma) {
		String hql = "FROM Juego j WHERE j.plataforma.idPlataforma = :idPlataforma";
		Query<Juego> query = session.createQuery(hql, Juego.class);
		query.setParameter("idPlataforma", idPlataforma);
		return query.getResultList();
	}

	// Método para obtener juegos por género
	public List<Juego> searchByGenero(Genero genero) {
		String hql = "FROM Juego j JOIN j.generos g WHERE g.idGenero = :idGenero";
		return session.createQuery(hql, Juego.class).setParameter("idGenero", genero.getIdGenero()).getResultList();
	}

}
