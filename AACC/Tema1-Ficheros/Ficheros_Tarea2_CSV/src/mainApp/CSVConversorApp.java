package mainApp;

import utils.CSVUtils;

public class CSVConversorApp {

	public static void main(String[] args) {

		String archivoCSV = "archivos/datos.csv";
		String archivoDat = "archivos/datos.dat";

		try {
			// Convierte CSV a Binario
			CSVUtils.fichero_CSV_To_Binario(archivoCSV);
			
			// Convierte Binario a CSV
			CSVUtils.fichero_Binario_To_CSV(archivoDat);

			// Ordena archivo CSV
			CSVUtils.ordenar_Archivo_CSV(archivoCSV);

			// Ordena archivo Binario
			CSVUtils.ordenar_Archivo_Binario(archivoDat);

			// Convierte Binario a CSV Ordenado
			CSVUtils.fichero_Binario_To_CSV_Ordenado(archivoDat);
			
			System.out.println("Operacion terminada con éxito");

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
