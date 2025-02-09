package psp.ud03.act303.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import psp.ud03.act303.utils.commands.CommandProcessorImpl;

/**
 * Clase que maneja a cada cliente con un hilo.
 * 
 * 
 * Los comandos son:
 * - list: Listar archivos en un directorio.
 * - delete: Eliminar un archivo o carpeta vacía.
 * - show: Mostrar el contenido de un archivo.
 * - quit: Cerrar la conexión con el cliente.
 * 
 * @author Emi
 * @version v1.0
 */
public class Worker extends Thread {

	private static final String OK = "OK";
	private static final String KO = "KO";
	// Reader para leer desde la conexión
	private BufferedReader input;
	// PrintWriter para enviar la conexión
	private PrintWriter output;
	// Procesador de comandos
	private CommandProcessorImpl commandProcessor;

	public Worker(Socket connection) throws IOException {
		// Creamos los flujos de entrada y salida a partir de la conexión
		input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		output = new PrintWriter(connection.getOutputStream());
		// Inicializamos el procesador de comandos
		commandProcessor = new CommandProcessorImpl(output);
	}

	@Override
	public void run() {
		try {
			String request;
			while ((request = input.readLine()) != null) {
				String[] parts = request.split(" ", 2);
				String command = parts[0];
				String argument = (parts.length > 1) ? parts[1] : "";

				switch (command) {
				case "list":
					commandProcessor.commandList(argument);
					break;
				case "delete":
					commandProcessor.commandDelete(argument);
					break;
				case "show":
					commandProcessor.commandShow(argument);
					break;
				case "quit":
					output.println(OK);
					output.flush();
					return;
				default:
					output.println(KO);
					output.flush();
					break;
				}
			}
		} catch (IOException e) {
			System.err.println("Error en la conexión con el cliente: " + e.getMessage());
		} finally {
			try {
				input.close();
				output.close();
			} catch (IOException e) {
				System.err.println("Error al cerrar los flujos: " + e.getMessage());
			}
		}
	}
}