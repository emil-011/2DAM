package psp.ud03.act303.utils.commands.prueba;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementación de la interfaz {@link CommandPruebaProcessorInt} que procesa
 * los comandos enviados por el cliente y realiza las operaciones
 * correspondientes en el servidor.
 * 
 * @author Emi
 * @version v1.0
 */
public class CommandPruebaProcessorImpl implements CommandPruebaProcessorInt {

	private static final String OK = "OK";
	private static final String KO = "KO";
	private PrintWriter output;

	// Constructor que recibe el PrintWriter
	public CommandPruebaProcessorImpl(PrintWriter output) {
		this.output = output;
	}

	@Override
	public void commandCreateDirectory(String path) {
		try {
			Path directoryPath = Paths.get(path);

			// Verificamos si el directorio ya existe
			if (Files.exists(directoryPath)) {
				sendResponse(KO);
				return;
			}

			// Creamos el directorio
			Files.createDirectory(directoryPath);
			sendResponse(OK);
		} catch (IOException e) {
			sendResponse(KO);
		}
	}

	@Override
	public void commandMove(String sourcePath, String targetPath) {
		try {
			Path source = Paths.get(sourcePath);
			Path target = Paths.get(targetPath);

			// Verificamos si la ruta de origen existe
			if (!Files.exists(source)) {
				sendResponse(KO);
				return;
			}

			// Movemos el archivo o directorio
			Files.move(source, target);
			sendResponse(OK);
		} catch (IOException e) {
			sendResponse(KO);
		}
	}

	@Override
	public void commandCopy(String sourcePath, String targetPath) {
		try {
			Path source = Paths.get(sourcePath);
			Path target = Paths.get(targetPath);

			// Verificamos si la ruta de origen existe
			if (!Files.exists(source)) {
				sendResponse(KO);
				return;
			}

			// Copiamos el archivo o directorio
			Files.copy(source, target);
			sendResponse(OK);
		} catch (IOException e) {
			sendResponse(KO); 
		}
	}

	@Override
	public void commandRename(String path, String newName) {
		try {
			Path source = Paths.get(path);
			Path target = source.getParent().resolve(newName);

			// Verificamos si la ruta de origen existe
			if (!Files.exists(source)) {
				sendResponse(KO);
				return;
			}

			// Renombramos el archivo o directorio
			Files.move(source, target);
			sendResponse(OK);
		} catch (IOException e) {
			sendResponse(KO);
		}
	}

	private void sendResponse(String response) {
		output.println(response);
		output.flush();
	}

	/*
	 * case "move": try { 
	 * String[] moveParts = argument.split(" ", 2); String sourcePath =
	 * moveParts[0]; String targetPath = moveParts[1];
	 * commandProcessorPrueba.commandMove(sourcePath, targetPath); } catch
	 * (ArrayIndexOutOfBoundsException e) { output.println(KO); output.flush(); }
	 * break;
	 */
}