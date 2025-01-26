package dao;

import java.util.List;
import org.hibernate.Session;
import model.Juegos;

public class JuegosDaoImpl extends CommonDaoImpl<Juegos> implements JuegosDaoInt {

    public JuegosDaoImpl(Session session) {
        super(session);
    }

    @Override
    public Juegos searchByTitle(String titulo) {
        String hql = "FROM Juegos j WHERE j.titulo = :titulo";
        return getSession().createQuery(hql, Juegos.class)
                .setParameter("titulo", titulo)
                .uniqueResult();
    }

    @Override
    public List<Juegos> searchFavourites() {
        String hql = "SELECT DISTINCT b.juego FROM Biblioteca b WHERE b.favorito = true";
        return getSession().createQuery(hql, Juegos.class).getResultList();
    }
}
