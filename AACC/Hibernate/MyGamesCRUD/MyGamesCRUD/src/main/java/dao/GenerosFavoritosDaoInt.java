package dao;

import models.GenerosFavoritos;
import java.util.List;

public interface GenerosFavoritosDaoInt extends CommonDaoInt<GenerosFavoritos> {

    /**
     * Busca todos los géneros favoritos de un usuario específico.
     * 
     * @param idUsuario El identificador del usuario cuyos géneros favoritos se desean obtener.
     * @return Una lista de objetos {@link GenerosFavoritos} asociada al usuario indicado.
     */
    public List<GenerosFavoritos> searchByUsuario(int idUsuario);

    /**
     * Busca todos los géneros favoritos asociados a un género específico.
     * 
     * @param idGenero El identificador del género cuyos géneros favoritos se desean obtener.
     * @return Una lista de objetos {@link GenerosFavoritos} asociada al género indicado.
     */
    public List<GenerosFavoritos> searchByGenero(int idGenero);
}
