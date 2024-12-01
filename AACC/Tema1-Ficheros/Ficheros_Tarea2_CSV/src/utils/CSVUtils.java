package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class CSVUtils {

	/**
	 * Convierte un archivo CSV en un archivo binario (.dat).
	 * 
	 * @param archivoCSV Nombre del archivo CSV a convertir.
	 * @throws IOException Si ocurre algún error durante la lectura o escritura del
	 *                     archivo.
	 */
	public static void fichero_CSV_To_Binario(String archivoCSV) throws IOException {
		// Verificamos que el archivo tenga la extensión correcta.
		verificarExtension(archivoCSV, ".csv");
		// Generamos el nombre para el archivo binario.
		String archivoBinario = archivoCSV.replace(".csv", ".dat");

		try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV));
				ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivoBinario))) {

			String linea;
			while ((linea = lector.readLine()) != null) {
				// Escribimos cada línea del CSV como un objeto en el archivo binario.
				escritor.writeObject(linea);
			}
		}
		// Registramos en el log.
		registrarLog("fichero_CSV_To_Binario", archivoCSV, archivoBinario);
	}

	/**
	 * Convierte un archivo binario (.dat) en un archivo CSV.
	 * 
	 * @param archivoBinario Nombre del archivo binario a convertir.
	 * @throws IOException            Si ocurre algún error durante la lectura o
	 *                                escritura del archivo.
	 * @throws ClassNotFoundException Si ocurre un error durante la deserialización
	 *                                de objetos.
	 */
	public static void fichero_Binario_To_CSV(String archivoBinario) {
		// Verificamos que el archivo tenga la extensión correcta.
		verificarExtension(archivoBinario , ".dat");
		// Generamos el nombre para el archivo CSV.
		String archivoCSV = archivoBinario.replace(".dat", ".csv");

		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivoBinario));
				BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCSV))) {

			Object objeto;
			while ((objeto = lector.readObject()) != null) {
				// Escribimos cada objeto leído en el CSV como una línea de texto.
				escritor.write(objeto.toString());
				escritor.newLine();
			}
		} catch (EOFException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		// Registramos en el log.
		registrarLog("fichero_Binario_To_CSV", archivoBinario, archivoCSV);
	}

	/**
	 * Ordena alfabéticamente un archivo CSV.
	 * 
	 * @param archivoCSV Nombre del archivo CSV a ordenar.
	 * @throws IOException Si ocurre algún error durante la lectura o escritura del
	 *                     archivo.
	 */
	public static void ordenar_Archivo_CSV(String archivoCSV) {
		// Verificamos que el archivo tenga la extensión correcta.
		verificarExtension(archivoCSV, ".csv");
		// Generamos el nombre para el archivo ordenado.
		String archivoOrdenado = archivoCSV.replace(".csv", "_ord.csv");

		// Lista para guardar cada linea del archivo
		List<String> lineas = new  ArrayList<>();
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivoCSV))){
			while (true) {
				lineas.add(lector.readObject().toString());
			}
			
		} catch (FileNotFoundException e1) {
			
		} catch (IOException e1) {
			
		} catch (ClassNotFoundException e) {
		}
				
			
	
		
		// Ordenamos las líneas ignorando mayúsculas y minúsculas.
		Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER);

		// Escribimos las líneas ordenadas en el nuevo archivo.
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoOrdenado))) {
			for (String linea : lineas) {
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {

		}

		registrarLog("ordenar_Archivo_CSV", archivoCSV, archivoOrdenado);
	}

	/**
	 * Ordena alfabéticamente un archivo binario (.dat).
	 * 
	 * @param archivoBinario Nombre del archivo binario a ordenar.
	 * @throws IOException            Si ocurre algún error durante la lectura o
	 *                                escritura del archivo.
	 * @throws ClassNotFoundException Si ocurre un error durante la deserialización
	 *                                de objetos.
	 */
	public static void ordenar_Archivo_Binario(String archivoBinario) throws IOException {
		// Verificamos que el archivo tenga la extensión correcta.
		verificarExtension(archivoBinario, ".dat");
		// Generamos el nombre para el archivo ordenado
		String archivoOrdenado = archivoBinario.replace(".dat", "_ord.dat");

		List<String> lineas = new ArrayList<>();

		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivoBinario))) {
			while (true) {
				// Almacenamos cada objeto como cadena de texto.
				lineas.add(lector.readObject().toString());
			}
		} catch (IOException | ClassNotFoundException e) {

			// Ordenamos las líneas ignorando mayúsculas y minúsculas.
			Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER);

			try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivoOrdenado))) {
				for (String linea : lineas) {
					// Escribimos las líneas ordenadas en el archivo binario.
					escritor.writeObject(linea);
				}
			} catch (IOException e1) {

			}
			registrarLog("ordenar_Archivo_Binario", archivoBinario, archivoOrdenado);
		}
	}

	/**
	 * Convierte un archivo binario (.dat) en un archivo CSV ordenado
	 * alfabéticamente.
	 * 
	 * @param archivoBinario Nombre del archivo binario a convertir y ordenar.
	 * @throws IOException            Si ocurre algún error durante la lectura o
	 *                                escritura del archivo.
	 * @throws ClassNotFoundException Si ocurre un error durante la deserialización
	 *                                de objetos.
	 */
	public static void fichero_Binario_To_CSV_Ordenado(String archivoBinario)
			throws IOException, ClassNotFoundException {
		// Verificamos que el archivo tenga la extensión correcta.
		verificarExtension(archivoBinario, ".dat");
		// Generamos el nombre para el archivo CSV ordenado.
		String archivoCSV = archivoBinario.replace(".dat", "_ord.csv");

		List<String> lineas = new ArrayList<>();

		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivoBinario))) {
			while (true) {
				// Almacenamos cada objeto como cadena de texto.
				lineas.add(lector.readObject().toString());
			}
		} catch (EOFException e) {

		}

		// Ordenamos las líneas ignorando mayúsculas y minúsculas.
		Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER);

		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCSV))) {
			for (String linea : lineas) {
				// Escribimos las líneas ordenadas en el archivo CSV.
				escritor.write(linea);
				escritor.newLine();
			}
		}
		// Registramos en el log
		registrarLog("fichero_Binario_To_CSV_Ordenado", archivoBinario, archivoCSV);
	}

	/**
	 * Verifica que el archivo tenga la extensión correcta.
	 * 
	 * @param archivo           Nombre del archivo.
	 * @param extensionEsperada Extensión esperada (por ejemplo, ".csv" o ".dat").
	 * @throws IllegalArgumentException Si el archivo no tiene la extensión
	 *                                  esperada.
	 */
	private static void verificarExtension(String archivo, String extensionEsperada) {
		if (!archivo.endsWith(extensionEsperada)) {
			throw new IllegalArgumentException("El archivo debe tener la extensión " + extensionEsperada);
		}
	}

	/**
	 * Registra el uso de un método en el archivo de log "log_csv.txt".
	 * 
	 * @param metodo         Nombre del método que se ejecutó.
	 * @param archivoEntrada Nombre del archivo de entrada.
	 * @param archivoSalida  Nombre del archivo de salida.
	 * @throws IOException Si ocurre algún error durante la escritura del archivo de
	 *                     log.
	 */
	private static void registrarLog(String metodo, String archivoEntrada, String archivoSalida) {
		// Archivo de log donde se almacenarán los registros.
		String logFile = "log_csv.txt";
		
		// Obtenemos la fecha y horo actual.
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		// Con el true no se sobreescribe
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(logFile, true))) {
			escritor.write("Método: " + metodo);
			escritor.write(", Fecha y hora: " + timeStamp);
			escritor.write(", Archivo entrada: " + archivoEntrada);
			escritor.write(", Archivo salida: " + archivoSalida);
			escritor.newLine();
		} catch (IOException e) {
			
		}
	}
}
