package mainApp;

import java.util.ArrayList;

import models.Empleado;
import utils.UtilsFicheroArray;

public class MainApp {
	public static void main(String[] args) {

		
		String rutaActual = System.getProperty("user.dir");
		String nombreArchivo = rutaActual+"/src/data/Empleados.txt";
		ArrayList<Empleado> empleados = UtilsFicheroArray.leer_Empleados(nombreArchivo);

		// Mostrar los empleados leídos por pantalla
		for (Empleado empleado : empleados) {
			System.out.println(empleado);
			System.out.println("----------------------------");
		}

		// Guardar en un archivo de texto
		UtilsFicheroArray.toArchivoTexto(empleados, "empleados_salida.txt");

		// Guardar en un archivo binario en orden inverso
		System.out.println("Escribiendo en un archivo binario en orden inverso");
		UtilsFicheroArray.toArchivoBinarioInverso(empleados, "empleados_salida.bin");

		// Leer desde el archivo binario
		System.out.println("\n\nEmpleados leídos desde el archivo binario:\n");
		UtilsFicheroArray.leer_Empleados_B("empleados_salida.bin");

	}

}
