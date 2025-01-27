package dao;

import models.Biblioteca;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BibliotecaDaoImpl extends CommonDaoImpl<Biblioteca> implements BibliotecaDaoInt {

    private final Session session;

    /**
     * Constructor de BibliotecaDaoImpl.
     *
     * @param session La sesión de Hibernate para interactuar con la base de datos.
     */
    public BibliotecaDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public List<Biblioteca> searchAll() {
        String hql = "FROM Biblioteca b";
        Query<Biblioteca> query = session.createQuery(hql, Biblioteca.class);
        return query.getResultList();
    }

    @Override
    public Biblioteca searchById(int idBiblioteca) {
        String hql = "FROM Biblioteca b WHERE b.idBiblioteca = :idBiblioteca";
        Query<Biblioteca> query = session.createQuery(hql, Biblioteca.class);
        query.setParameter("idBiblioteca", idBiblioteca);
        return query.uniqueResult();
    }

    @Override
    public List<Biblioteca> searchByUsuario(int idUsuario) {
        String hql = "FROM Biblioteca b WHERE b.usuario.idUsuario = :idUsuario";
        Query<Biblioteca> query = session.createQuery(hql, Biblioteca.class);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public List<Biblioteca> searchByJuego(int idJuego) {
        String hql = "FROM Biblioteca b WHERE b.juego.idJuego = :idJuego";
        Query<Biblioteca> query = session.createQuery(hql, Biblioteca.class);
        query.setParameter("idJuego", idJuego);
        return query.getResultList();
    }
}
