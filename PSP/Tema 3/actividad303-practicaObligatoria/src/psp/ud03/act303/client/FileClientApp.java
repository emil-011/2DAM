package psp.ud03.act303.client;

import java.io.IOException;
import java.util.Scanner;

public class FileClientApp {

	// Valores por defecto para el host y el puerto
	private static final String DEFAULT_HOST = "192.168.1.234";
	private static final int DEFAULT_PORT = 2121;
	private static final String OK = "OK";
	private static final String KO = "KO";
	private static Scanner sc;
	private String serverHost;
	private int serverPort;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		FileClientApp app = new FileClientApp();
		app.run(args);
	}

	private void run(String[] args) {
		// Procesamos los argumentos
		processArguments(args);
		// Conectar al servidor
		connectToServer(serverHost, serverPort);
	}

	private void processArguments(String[] args) {
		try {
			serverHost = args[0];
		} catch (Exception e) {
			serverHost = DEFAULT_HOST;
		}
		try {
			serverPort = Integer.parseInt(args[1]);
		} catch (Exception e) {
			serverPort = DEFAULT_PORT;
		}
	}

	// Método para manejar la conexion del usuario con el servidor
	private void connectToServer(String host, int port) {
		// Conectamos al servidor
		try (Client client = new Client(host, port)) {

			System.out.println("Conectado al servidor en " + host + ":" + port);

			for (;;) {
				System.out.print("Introduce un comando (list, delete, show, quit): ");
				String command = sc.nextLine();

				// Enviamos el comando al servidor
				String response = client.sendCommand(command);

				// Procesamos la respuesta del servidor
				if (response.equals(OK)) {
					System.out.println(OK);

					if (command.startsWith("list")) {
						// Leemos y mostramos el listado de archivos
						String listing = client.readList();
						System.out.println(listing);
					} else if (command.startsWith("show")) {
						// Leemos y mostramos el contenido del archivo
						String fileContent = client.readShow();
						System.out.println(fileContent);
					}

				} else if (response.equals(KO)) {
					System.out.println(KO);
				}

				// Cerramos la conexión cuando el comando sea quit
				if (command.equals("quit")) {
					System.out.println("Cerrando la conexión...");
					break;
				}
			}

		} catch (IOException e) {
			System.err.println("Error de conexión: " + e.getMessage());
		}
	}
}