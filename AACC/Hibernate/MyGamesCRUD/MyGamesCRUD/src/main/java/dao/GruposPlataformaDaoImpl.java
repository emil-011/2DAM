package dao;

import models.GrupoPlataformas;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class GruposPlataformaDaoImpl extends CommonDaoImpl<GrupoPlataformas> implements GruposPlataformaDaoInt {

    private final Session session;

    /**
     * Constructor para GrupoPlataformasDaoImpl.
     *
     * @param session La sesión de Hibernate para interactuar con la base de datos.
     */
    public GruposPlataformaDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    /**
     * Busca un grupo de plataformas por su nombre.
     *
     * @param nombre Nombre del grupo de plataformas.
     * @return Lista de grupos de plataformas que coinciden con el nombre.
     */
    @Override
    public List<GrupoPlataformas> searchByNombre(String nombre) {
        String hql = "FROM GrupoPlataformas g WHERE LOWER(g.nombre) LIKE LOWER(:nombre)";
        Query<GrupoPlataformas> query = session.createQuery(hql, GrupoPlataformas.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }
}
