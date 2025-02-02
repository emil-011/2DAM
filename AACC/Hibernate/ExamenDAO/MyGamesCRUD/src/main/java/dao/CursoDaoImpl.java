package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Curso;

import java.util.List;

public class CursoDaoImpl implements CursoDaoInt {

    private EntityManager entityManager;

    public CursoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Curso curso) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Curso curso) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Curso curso) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(curso) ? curso : entityManager.merge(curso));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> searchAll() {
        return entityManager.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

}