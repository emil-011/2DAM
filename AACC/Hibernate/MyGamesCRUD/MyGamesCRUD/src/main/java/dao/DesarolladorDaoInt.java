package dao;

import java.util.List;
import models.Desarollador;

public interface DesarolladorDaoInt extends CommonDaoInt<Desarollador> {


    /**
     * Busca un desarrollador por su identificador único.
     * 
     * @param idDesarrollador El identificador único del desarrollador.
     * @return Un objeto {@link Desarollador} que corresponde al id proporcionado, o
     *         {@code null} si no se encuentra.
     */
    Desarollador searchById(int idDesarrollador);

    /**
     * Busca todos los desarrolladores que coinciden con un nombre específico.
     * 
     * @param nombre El nombre del desarrollador que se quiere buscar.
     * @return Una lista de objetos {@link Desarollador} que coinciden con el nombre
     *         indicado.
     */
    List<Desarollador> searchByNombre(String nombre);

    /**
     * Busca todos los desarrolladores que están asociados a un país específico.
     * 
     * @param pais El país del que se quieren obtener los desarrolladores.
     * @return Una lista de objetos {@link Desarollador} que están asociados al país
     *         indicado.
     */
    List<Desarollador> searchByPais(String pais);
}
