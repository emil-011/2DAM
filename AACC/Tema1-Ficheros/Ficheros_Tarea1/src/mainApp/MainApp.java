package mainApp;

import java.util.List;

import models.Empleado;
import utils.UtilsFicheroArray;

public class MainApp {

	public static void main(String[] args) {
		// Primer método
		System.out.println("-------------------------------------------------------");
		imprimirConAnimacion("Leyendo Archivo, creando lista de empleados...");
		List<Empleado> empleados = UtilsFicheroArray.empleadosToArray();
		imprimirConAnimacion("Lista de empleados creada con éxito");
		System.out.println("-------------------------------------------------------");

		// Segundo método
		imprimirConAnimacion("Almacenando lista de empleados en un archivo...");
		empleados.add(new Empleado("Ayuntamiento de Gijón/Xixón", 17, 1));
		empleados.add(new Empleado("Ayuntamiento de Gijón/Xixón", 19, 1));
		empleados.add(new Empleado("Ayuntamiento de Gijón/Xixón", 21, 2));
		empleados.add(new Empleado("Ayuntamiento de Gijón/Xixón", 22, 2));

		UtilsFicheroArray.toArchivoTexto(empleados, "arch\\hola.txt");
		imprimirConAnimacion("Archivo creado con éxito.");
		System.out.println("-------------------------------------------------------");

		// Tercer Método
		imprimirConAnimacion("Almacenando lista de empleados en un archivo binario...");
		UtilsFicheroArray.toArchivoBinarioInverso(empleados);
		imprimirConAnimacion("Fichero binario creado con éxito.");
		System.out.println("-------------------------------------------------------");

		// Cuarto Método
		imprimirConAnimacion("Leyendo archivo de texto...");
		imprimirConAnimacion("Imprimiendo listado de empleados");
		List<Empleado> empleadosLeidos = UtilsFicheroArray.leerEmpleados("arch\\empleados.txt");
		for (Empleado empleado : empleadosLeidos) {
			System.out.println(empleado);
		}
		System.out.println("--------------------------------------------------------");

		// Leer empleados de archivo binario (deberías ajustar esto si es necesario)
		imprimirConAnimacion("Leyendo archivo binario...");
		List<Empleado> empleadosLeidosBinario = UtilsFicheroArray.leerEmpleados("arch\\empleados.txt");
		for (Empleado empleado : empleadosLeidosBinario) {
			System.out.println(empleado);
		}
		System.out.println("--------------------------------------------------------");
	}

	// Metodo para hacer una "animacion" al escribir
	private static void imprimirConAnimacion(String mensaje) {
		for (char letra : mensaje.toCharArray()) {
			System.out.print(letra);
			try {
				Thread.sleep(10);//Tiempo entre ch y ch
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
	
}
