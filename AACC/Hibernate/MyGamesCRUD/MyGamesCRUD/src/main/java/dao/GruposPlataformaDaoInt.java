package dao;

import models.GrupoPlataformas;

import java.util.List;

public interface GruposPlataformaDaoInt extends CommonDaoInt<GrupoPlataformas> {

    /**
     * Busca un grupo de plataformas por su nombre.
     *
     * @param nombre Nombre del grupo de plataformas.
     * @return Lista de grupos de plataformas que coinciden con el nombre.
     */
    List<GrupoPlataformas> searchByNombre(String nombre);
}
