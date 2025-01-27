package dao;

import models.Wishlist;
import java.util.List;

public interface WishlistDaoInt extends CommonDaoInt<Wishlist> {

    /**
     * Obtiene la lista de deseos de un usuario específico.
     * 
     * @param idUsuario El ID del usuario.
     * @return Lista de objetos Wishlist asociados al usuario.
     */
    List<Wishlist> searchByUsuario(int idUsuario);

    /**
     * Obtiene la lista de deseos asociados a un juego específico.
     * 
     * @param idJuego El ID del juego.
     * @return Lista de objetos Wishlist asociados al juego.
     */
    List<Wishlist> searchByJuego(int idJuego);

    /**
     * Busca una entrada específica en la lista de deseos basada en el usuario y el juego.
     * 
     * @param idUsuario El ID del usuario.
     * @param idJuego El ID del juego.
     * @return Un objeto Wishlist que coincide con el usuario y el juego.
     */
    Wishlist searchByUsuarioAndJuego(int idUsuario, int idJuego);

    /**
     * Obtiene todas las entradas de la lista de deseos.
     * 
     * @return Lista de objetos Wishlist.
     */
    List<Wishlist> searchAll();
}
