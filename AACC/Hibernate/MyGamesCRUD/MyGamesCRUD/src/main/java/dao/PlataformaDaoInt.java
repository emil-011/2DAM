package dao;

import models.Plataforma;

import java.util.List;

public interface PlataformaDaoInt extends CommonDaoInt<Plataforma> {

    /**
     * Busca todas las plataformas asociadas a un grupo específico.
     *
     * @param idGrupo ID del grupo de plataformas.
     * @return Lista de plataformas asociadas al grupo.
     */
    List<Plataforma> searchByGrupo(int idGrupo);

    /**
     * Busca una plataforma por su nombre.
     *
     * @param nombre Nombre de la plataforma.
     * @return Una lista de plataformas que coinciden con el nombre.
     */
    List<Plataforma> searchByNombre(String nombre);
}
