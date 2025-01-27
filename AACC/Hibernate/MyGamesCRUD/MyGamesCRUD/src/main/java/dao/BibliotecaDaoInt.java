package dao;

import models.Biblioteca;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Biblioteca.
 */
public interface BibliotecaDaoInt extends CommonDaoInt<Biblioteca> {

    /**
     * Obtiene todas las entradas de la biblioteca.
     * 
     * @return Una lista de objetos {@link Biblioteca} que representan todas las
     *         entradas en la biblioteca.
     */
    List<Biblioteca> searchAll();

    /**
     * Busca una entrada de la biblioteca por su identificador único.
     * 
     * @param idBiblioteca El identificador único de la entrada en la biblioteca.
     * @return Un objeto {@link Biblioteca} que corresponde al id proporcionado, o
     *         {@code null} si no se encuentra.
     */
    Biblioteca searchById(int idBiblioteca);

    /**
     * Busca todas las entradas de la biblioteca asociadas a un usuario específico.
     * 
     * @param idUsuario El identificador del usuario cuya biblioteca se quiere
     *                  obtener.
     * @return Una lista de objetos {@link Biblioteca} asociada al usuario indicado.
     */
    List<Biblioteca> searchByUsuario(int idUsuario);

    /**
     * Busca todas las entradas de la biblioteca asociadas a un juego específico.
     * 
     * @param idJuego El identificador del juego cuya biblioteca se quiere obtener.
     * @return Una lista de objetos {@link Biblioteca} asociada al juego indicado.
     */
    List<Biblioteca> searchByJuego(int idJuego);
}
