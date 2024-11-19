package jdbc.main;

import java.sql.SQLException;
import jdbc.conexion.Funciones;

public class Main {
	public static void main(String[] args) throws SQLException {
		// Llama a la funci�n que ejecuta el programa.
		Funciones.crearTablaSuperusuarios("Alumnado_nuevo.txt");
	}
}
