package dao;

import models.Genero;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class GeneroDaoImpl extends CommonDaoImpl<Genero> implements GeneroDaoInt {
    // Session de la base de datos
    private Session session;

    /**
     * Constructor Genero DAO
     * 
     * @param session Session de la base de datos
     */
    public GeneroDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public List<Genero> searchAll() {
        List<Genero> generos = null;

        try {
            session.beginTransaction();
            Query<Genero> query = session.createQuery("FROM Genero", Genero.class);
            generos = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return generos;
    }

    @Override
    public Genero searchById(String idGenero) {
        Genero genero = null;

        try {
            session.beginTransaction();
            genero = session.get(Genero.class, idGenero);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return genero;
    }

    @Override
    public List<Genero> searchByNombre(String nombre) {
        List<Genero> generos = null;

        try {
            session.beginTransaction();
            Query<Genero> query = session.createQuery("FROM Genero WHERE nombre LIKE :nombre", Genero.class);
            query.setParameter("nombre", "%" + nombre + "%");  // LIKE para buscar coincidencias parciales
            generos = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }

        return generos;
    }
}
