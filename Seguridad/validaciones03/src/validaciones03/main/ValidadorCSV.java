package validaciones03.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidadorCSV {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("USO: java -jar ValidadorCSV <archivoCSV>");
			return;
		}

		String archivo = args[0];

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			int numeroLinea = 0;

			while ((linea = reader.readLine()) != null) {
				numeroLinea++;
				String[] datos = parseCSVLine(linea);

				if (datos.length != 10) {
					System.err.printf("Error en la línea %d: Número de campos incorrecto (%d en lugar de 10)%n",
							numeroLinea, datos.length);
					return;
				}

				boolean esValido = Valida.validarCampos(datos, numeroLinea);
				if (esValido) {
					System.out.printf("Línea %d validada con éxito: %s%n", numeroLinea, datos[7]); // Username
				}
			}
		} catch (IOException e) {
			System.err.printf("Error al leer el archivo: %s%n", e.getMessage());
		}
	}

	// Divide las líneas del CSV
	private static String[] parseCSVLine(String linea) {
		return linea.split(",");
	}
}
