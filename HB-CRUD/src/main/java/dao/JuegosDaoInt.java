package dao;

import java.util.List;
import model.Juegos;

public interface JuegosDaoInt extends CommonDaoInt<Juegos> {

    /**
     * Busca un juego por título.
     * 
     * @param titulo - El título del juego.
     * @return El juego correspondiente al título especificado.
     */
    Juegos searchByTitle(String titulo);

    /**
     * Busca todos los juegos marcados como favoritos.
     * 
     * @return Lista de juegos favoritos.
     */
    List<Juegos> searchFavourites();
}
