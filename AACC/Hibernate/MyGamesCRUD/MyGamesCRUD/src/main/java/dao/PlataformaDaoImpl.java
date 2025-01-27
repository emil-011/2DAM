package dao;

import models.Plataforma;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PlataformaDaoImpl extends CommonDaoImpl<Plataforma> implements PlataformaDaoInt {

    private final Session session;

    /**
     * Constructor para PlataformaDaoImpl.
     *
     * @param session La sesión de Hibernate para interactuar con la base de datos.
     */
    public PlataformaDaoImpl(Session session) {
        super(session);
        this.session = session;
    }

    /**
     * Busca todas las plataformas asociadas a un grupo específico.
     *
     * @param idGrupo ID del grupo de plataformas.
     * @return Lista de plataformas asociadas al grupo.
     */
    @Override
    public List<Plataforma> searchByGrupo(int idGrupo) {
        String hql = "FROM Plataforma p WHERE p.grupoPlataforma.idGrupo = :idGrupo";
        Query<Plataforma> query = session.createQuery(hql, Plataforma.class);
        query.setParameter("idGrupo", idGrupo);
        return query.getResultList();
    }

    /**
     * Busca una plataforma por su nombre.
     *
     * @param nombre Nombre de la plataforma.
     * @return Una lista de plataformas que coinciden con el nombre.
     */
    @Override
    public List<Plataforma> searchByNombre(String nombre) {
        String hql = "FROM Plataforma p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)";
        Query<Plataforma> query = session.createQuery(hql, Plataforma.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }
}
