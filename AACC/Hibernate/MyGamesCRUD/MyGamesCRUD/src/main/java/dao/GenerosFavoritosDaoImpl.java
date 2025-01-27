package dao;

import models.GenerosFavoritos;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class GenerosFavoritosDaoImpl extends CommonDaoImpl<GenerosFavoritos> implements GenerosFavoritosDaoInt {

    private Session session;

    /**
     * Constructor para el DAO de Géneros Favoritos
     * 
     * @param session Session de la base de datos
     */
    public GenerosFavoritosDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public List<GenerosFavoritos> searchByUsuario(int idUsuario) {
        String hql = "FROM GenerosFavoritos gf WHERE gf.usuario.idUsuario = :idUsuario";
        Query<GenerosFavoritos> query = session.createQuery(hql, GenerosFavoritos.class);
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public List<GenerosFavoritos> searchByGenero(int idGenero) {
        String hql = "FROM GenerosFavoritos gf WHERE gf.genero.idGenero = :idGenero";
        Query<GenerosFavoritos> query = session.createQuery(hql, GenerosFavoritos.class);
        query.setParameter("idGenero", idGenero);
        return query.getResultList();
    }
}
