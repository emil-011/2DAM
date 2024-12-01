package jdbc.conexion;

import java.sql.*;

// Hago la conexi�n a la base de datos.
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BBDD = "bd_neptuno2";
    private static final String PARAMETROS = "?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL + BBDD + PARAMETROS, USUARIO, CLAVE);
            System.out.println("Conexion OK");
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }

        return conexion;
    }
}