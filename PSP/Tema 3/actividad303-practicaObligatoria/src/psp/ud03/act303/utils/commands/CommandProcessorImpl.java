package psp.ud03.act303.utils.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementación de la interfaz {@link CommandProcessorInt} que procesa los comandos
 * enviados por el cliente y realiza las operaciones correspondientes en el servidor.
 * 
 * @author Emi
 * @version v1.0
 */
public class CommandProcessorImpl implements CommandProcessorInt {

	private static final String OK = "OK";
	private static final String KO = "KO";
	private PrintWriter output;

	// Constructor que recibe el PrintWriter
	public CommandProcessorImpl(PrintWriter output) {
		this.output = output;
	}

	@Override
	public void commandList(String path) {
		try {
			// Convertimos el string a un path
			Path directory = Paths.get(path);
			// Verificamos si la ruta es un directorio válido
			if (Files.isDirectory(directory)) {
				// Enviamos "OK" al cliente
				output.println(OK);
				// Para cada archivo del directorio
				Files.list(directory).forEach(file -> {
					try {
						// Obtenemos el nombre del archivo
						String fileName = file.getFileName().toString();
						// Obtenemos el tamaño del archivo en KiB
						long sizeInBytes = Files.size(file);
						double sizeInKiB = sizeInBytes / 1024.0;
						String formattedSize = String.format("%.2f KiB", sizeInKiB);
						output.println(fileName + " " + formattedSize);
					} catch (IOException e) {
						// Si hay un error al obtener el tamaño, enviamos KO
						output.println(KO);
					}
				});
				// Línea vacía para indicar el fin del listado
				output.println();
				output.flush();
			} else {
				// Si la ruta no es un directorio válido, enviamos KO
				output.println(KO);
				output.flush();
			}
		} catch (IOException e) {
			// Si hay un error al procesar la ruta, enviamos KO
			output.println(KO);
			output.flush();
		}
	}

	@Override
	public void commandShow(String path) {
		try {
			Path filePath = Paths.get(path);

			// Verificamos si la ruta existe
			if (!Files.exists(filePath)) {
				output.println(KO);
				output.flush();
				return;
			}

			// Verificamos si es un archivo regular
			if (Files.isRegularFile(filePath) && !Files.isDirectory(filePath)) {
				output.println(OK);
				// Leemos el archivo
				try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
					String line;
					List<String> lines = new ArrayList<>();
					while ((line = reader.readLine()) != null) {
						lines.add(line);
					}
					int numLines = lines.size();
					// Imprimimos el número de líneas
					output.println(numLines);
					output.flush();
					// Después imprimimos el contenido del archivo
					for (String fileLine : lines) {
						output.println(fileLine);
					}
					output.flush();
				}
			} else {
				sendResponse(KO);
			}
		} catch (IOException e) {
			sendResponse(path);
		}
	}

	@Override
	public void commandDelete(String path) {
		Path filePath = Paths.get(path);

		// Verificamos si la ruta existe
		if (!Files.exists(filePath)) {
			sendResponse(KO);
			return;
		}

		try {
			// Si es un archivo, intentamos eliminarlo
			if (Files.isRegularFile(filePath)) {
				deleteFile(filePath);
				return;
			}

			// Si es un directorio, verificamos si está vacío antes de eliminarlo
			if (Files.isDirectory(filePath)) {
				deleteDirectoryIfEmpty(filePath);
				return;
			}

			// Si no es un archivo ni un directorio
			sendResponse(KO);
		} catch (IOException e) {
			sendResponse(KO);
		}
	}

	// Elimina un archivo.
	private void deleteFile(Path filePath) throws IOException {
		if (Files.deleteIfExists(filePath)) {
			sendResponse(OK);
		} else {
			sendResponse(KO);
		}
	}

	// Elimina un directorio si está vacío.
	private void deleteDirectoryIfEmpty(Path directoryPath) throws IOException {
		if (isDirectoryEmpty(directoryPath)) {
			if (Files.deleteIfExists(directoryPath)) {
				sendResponse(OK);
			} else {
				sendResponse(KO);
			}
		} else {
			sendResponse(KO);
		}
	}


	// Veridfica si un directorio esta vacío
	private boolean isDirectoryEmpty(Path directoryPath) throws IOException {
		try (Stream<Path> stream = Files.list(directoryPath)) {
			// Si no hay elementos, está vacío
			return !stream.findAny().isPresent(); 
		}
	}

	private void sendResponse(String response) {
		output.println(response);
		output.flush();
	}
}