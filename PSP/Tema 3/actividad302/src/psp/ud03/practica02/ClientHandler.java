// psp.ud03.practica02.ClientHandler
package psp.ud03.practica02;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {

	private final Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {

			// Leemos la ruta del archivo solicitado
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String filePath = reader.readLine();

			// Verificamos si el archivo existe y es accesible
			Path path = Paths.get(filePath);
			if (Files.exists(path) && Files.isRegularFile(path) && Files.isReadable(path)) {
				// Enviar respuesta OK
				out.write("OK\r\n".getBytes());
				out.flush();

				// Enviamos el archivo
				Files.copy(path, out);
			} else {
				// Enviamos respuesta KO
				out.write("KO\r\n".getBytes());
				out.flush();
			}
		} catch (IOException e) {
			System.err.println("Error al manejar la solicitud del cliente: " + e.getMessage());
		} finally {
			try {
				// Cerramos el socket
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("Error al cerrar la conexión del cliente: " + e.getMessage());
			}
		}
	}
}