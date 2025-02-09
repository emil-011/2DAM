package psp.ud03.act303.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Clase principal del servidor de archivos.
 * 
 * Se encarga de gestionar las conexiones con los clientes y procesar
 * sus solicitudes: [listar, mostrar contenido y borrar archivos]
 * 
 * Crea un hilo independiente (Worker) para cada cliente que se conecta.
 * 
 * @author Emi
 * @version v1.0
 */
public class FileServerApp {

	private static final String CONFIG_FILE = "server.properties";
	private static final int DEFAULT_PORT = 2121;

	public static void main(String[] args) {
		proccessArguments(args);
		FileServerApp app = new FileServerApp();
		app.run();
	}
	
	
	private void run() {
		// Leemos la configuración del archivo server.properties
		int port = readServerConfig();

		// Iniciamos el servidor
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor iniciado en el puerto " + port);

			// Aceptamos conexiones de clientes
			for (;;) {
				Socket conexion = serverSocket.accept();
				System.out.println("Nuevo cliente conectado: " + conexion.getInetAddress());

				// Creamos un hilo por cliente
				Worker worker = new Worker(conexion);
				worker.start();
			}
		} catch (IOException e) {
			System.err.println("Error al iniciar el servidor: " + e.getMessage());
		}
	}

	/**
	 * Lee la configuración del servidor desde el archivo server.properties. Si no
	 * se puede leer el archivo o el puerto no es válido, se usa el puerto por
	 * defecto.
	 *
	 * @return El puerto configurado o el puerto por defecto.
	 */
	private static int readServerConfig() {
		Properties properties = new Properties();
		try (InputStream input = FileServerApp.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
			if (input != null) {
				properties.load(input);
				String portString = properties.getProperty("puerto");
				if (portString != null) {
					return Integer.parseInt(portString);
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error al leer la configuración. Usando puerto por defecto: " + DEFAULT_PORT);
		}
		return DEFAULT_PORT;
	}

	// Verifica si se han pasado argumentos
	private static void proccessArguments(String[] args) {
		if (args.length > 0) {
			System.err.println("Este programa no acepta argumentos.");
			return;
		}
	}
}