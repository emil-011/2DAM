package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Inscripcion;

import java.util.List;

public class InscripcionDaoImpl implements InscripcionDaoInt {

	private EntityManager entityManager;

	public InscripcionDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void insert(Inscripcion inscripcion) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(inscripcion);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void update(Inscripcion inscripcion) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(inscripcion);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Inscripcion inscripcion) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(entityManager.contains(inscripcion) ? inscripcion : entityManager.merge(inscripcion));
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Inscripcion> searchAll() {
		return entityManager.createQuery("SELECT c FROM Inscripcion c", Inscripcion.class).getResultList();
	}

}