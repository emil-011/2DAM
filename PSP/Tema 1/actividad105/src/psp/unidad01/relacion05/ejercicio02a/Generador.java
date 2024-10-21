package psp.unidad01.relacion05.ejercicio02a;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Generador {

	public static void main(String[] args) {
		String ruta = "C:\\Users\\Alumnado2DAM\\eclipse-workspace\\actividad105\\archivos\\textoPrueba.txt";

		// Creamos el archivo y escribimos en el
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(ruta, true))) {
			bf.newLine();
			bf.write("Hola");
			bf.newLine();
			bf.write("Adios");
		} catch (Exception e) {
			System.err.println("ERROR" + e.getMessage());
		}
		System.out.println("Archivo creado correctamente");
	}
}
