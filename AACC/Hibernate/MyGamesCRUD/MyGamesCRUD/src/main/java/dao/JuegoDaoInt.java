package dao;

import models.Genero;
import models.Juego;

import java.util.Date;
import java.util.List;

public interface JuegoDaoInt extends CommonDaoInt<Juego> {

    /**
     * Busca juegos por su título.
     *
     * @param titulo Título del juego que se desea buscar.
     * @return Lista de juegos que coinciden con el título.
     */
    List<Juego> searchByTitulo(String titulo);

    /**
     * Obtiene todos los juegos lanzados después de una fecha específica.
     *
     * @param fecha Fecha de lanzamiento mínima.
     * @return Lista de juegos lanzados después de la fecha especificada.
     */
    List<Juego> searchByFechaLanzamiento(Date fecha);

    /**
     * Busca juegos creados por un desarrollador específico.
     *
     * @param idDesarrollador ID del desarrollador.
     * @return Lista de juegos creados por el desarrollador indicado.
     */
    List<Juego> searchByDesarrollador(int idDesarrollador);

    /**
     * Busca juegos asociados con un género específico.
     *
     * @param idGenero ID del género.
     * @return Lista de juegos asociados al género indicado.
     */
    List<Juego> searchByGenero(Genero genero);

    /**
     * Busca juegos asociados con una plataforma específica.
     *
     * @param idPlataforma ID de la plataforma.
     * @return Lista de juegos asociados a la plataforma indicada.
     */
    List<Juego> searchByPlataforma(int idPlataforma);
}
