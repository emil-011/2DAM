package dao;

import models.Desarollador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DesarolladorDaoImpl extends CommonDaoImpl<Desarollador> implements DesarolladorDaoInt {

	public DesarolladorDaoImpl(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	private SessionFactory sessionFactory;

	@Override
	public List<Desarollador> searchAll() {
		Session session = sessionFactory.openSession();
		List<Desarollador> desarrolladores = null;

		try {
			session.beginTransaction();
			Query<Desarollador> query = session.createQuery("FROM Desarollador", Desarollador.class);
			desarrolladores = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return desarrolladores;
	}

	@Override
	public Desarollador searchById(int idDesarrollador) {
		Session session = sessionFactory.openSession();
		Desarollador desarrollador = null;

		try {
			session.beginTransaction();
			desarrollador = session.get(Desarollador.class, idDesarrollador);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return desarrollador;
	}

	@Override
	public List<Desarollador> searchByNombre(String nombre) {
		Session session = sessionFactory.openSession();
		List<Desarollador> desarrolladores = null;

		try {
			session.beginTransaction();
			Query<Desarollador> query = session.createQuery("FROM Desarollador WHERE nombre = :nombre",
					Desarollador.class);
			query.setParameter("nombre", nombre);
			desarrolladores = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return desarrolladores;
	}

	@Override
	public List<Desarollador> searchByPais(String pais) {
		Session session = sessionFactory.openSession();
		List<Desarollador> desarrolladores = null;

		try {
			session.beginTransaction();
			Query<Desarollador> query = session.createQuery("FROM Desarollador WHERE pais = :pais", Desarollador.class);
			query.setParameter("pais", pais);
			desarrolladores = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return desarrolladores;
	}
}
