package dao;

import models.Wishlist;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class WishlistDaoImpl extends CommonDaoImpl<Wishlist> implements WishlistDaoInt {

    private final Session session;

    /**
     * Constructor de WishlistDaoImpl.
     *
     * @param session La sesión de Hibernate para interactuar con la base de datos.
     */
    public WishlistDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public List<Wishlist> searchByUsuario(int idUsuario) {
        String hql = "FROM Wishlist w WHERE w.usuario.idUsuario = :idUsuario";
        Query<Wishlist> query = session.createQuery(hql, Wishlist.class);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public List<Wishlist> searchByJuego(int idJuego) {
        String hql = "FROM Wishlist w WHERE w.juego.idJuego = :idJuego";
        Query<Wishlist> query = session.createQuery(hql, Wishlist.class);
        query.setParameter("idJuego", idJuego);
        return query.getResultList();
    }

    @Override
    public Wishlist searchByUsuarioAndJuego(int idUsuario, int idJuego) {
        String hql = "FROM Wishlist w WHERE w.usuario.idUsuario = :idUsuario AND w.juego.idJuego = :idJuego";
        Query<Wishlist> query = session.createQuery(hql, Wishlist.class);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("idJuego", idJuego);
        return query.uniqueResult();
    }

    @Override
    public List<Wishlist> searchAll() {
        String hql = "FROM Wishlist w";
        Query<Wishlist> query = session.createQuery(hql, Wishlist.class);
        return query.getResultList();
    }
}
