package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Usuario;

import java.util.List;

public class UsuarioDaoImpl implements UsuarioDaoInt {

	private EntityManager entityManager;

	public UsuarioDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void insert(Usuario usuario) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void update(Usuario usuario) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Usuario usuario) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(entityManager.contains(usuario) ? usuario : entityManager.merge(usuario));
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Usuario> searchAll() {
	    return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
	

}