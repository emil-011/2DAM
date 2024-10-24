package cuentavocales.ejercicio01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CuentaLetraApp {

	private static final String ARCHIVO_A_CONTAR = "TextoVocales.txt";

	public static void main(String[] args) {
		
		
		if (args.length != 2) {
			System.out.println("Usa: CuentaLetraAPP <vocal> <archivo_salida.txt>");
			return;
		}
		// Obtener la vocal a contar y el archivo de salida
		char vocal = args[0].toUpperCase().charAt(0);
		String archivoSalida = args[1];
		int contadorVocales = 0;

		// Marcamos el inicio del tiempo
		long startTime = System.currentTimeMillis();

		// Leer el archivo de entrada y contar las vocales
		try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_A_CONTAR))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					// Comprobar si el carácter es la vocal que estamos buscando
					char c = linea.charAt(i);
					if (c == vocal || c == Character.toLowerCase(vocal) || c == Character.toUpperCase(vocal)) {
						contadorVocales++;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}

		// Marcar el final del tiempo
		long endTime = System.currentTimeMillis();
		// Tiempo total
		long totalTime = endTime - startTime;

		// Escribimos en el archivo de salida los resultados
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida, true))) {
			writer.write("Se ha encontrado la letra " + vocal + " un total de " + contadorVocales
					+ " veces en el archivo de texto " + ARCHIVO_A_CONTAR + " | El tiempo total del programa fue: "
					+ totalTime +" ms.");
			writer.newLine();
		} catch (Exception e) {
			System.err.println("Error al escribir el archivo de salida: " + e.getMessage());
		}
		
	}

}