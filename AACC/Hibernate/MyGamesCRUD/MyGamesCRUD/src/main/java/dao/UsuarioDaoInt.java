package dao;

import models.Genero;
import models.Usuario;
import java.util.List;

public interface UsuarioDaoInt extends CommonDaoInt<Usuario> {

    /**
     * Busca un usuario por su id.
     * 
     * @param idUsuario El identificador único del usuario.
     * @return El objeto Usuario asociado al id proporcionado.
     */
    Usuario searchById(int idUsuario);

    /**
     * Obtiene todos los usuarios.
     * 
     * @return Una lista de objetos Usuario.
     */
    List<Usuario> searchAll();

    /**
     * Busca los géneros favoritos de un usuario.
     * 
     * @param idUsuario El id del usuario.
     * @return Lista de géneros favoritos del usuario.
     */
    List<Genero> searchGenerosFavoritos(int idUsuario);

    /**
     * Asocia géneros favoritos a un usuario.
     * 
     * @param idUsuario El id del usuario.
     * @param generos   Lista de géneros a asociar al usuario.
     */
    void setGenerosFavoritos(int idUsuario, List<Genero> generos);
}
