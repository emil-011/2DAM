package psp.ud03.practica01;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

public class MainDNSServerApp {
	private static final int PORT = 2222;
	private static final int BUFFER_LENGTH = 1024;
	private static final String PROPERTIES_FILE = "dns.properties";

	public static void main(String[] args) {
		MainDNSServerApp app = new MainDNSServerApp();
		app.run();
	}

	private void run() {
		Properties dnsProperties = new Properties();

		// Cargamos el archivo dns.properties
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			dnsProperties.load(fis);
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo dns.properties: " + e.getMessage());
			return;
		}

		System.out.println("Servidor DNS iniciado en el puerto " + PORT);

		// Configuramos el socket UDP
		try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
			byte[] receiveBuffer = new byte[BUFFER_LENGTH];

			// Mientras no de excepción
			for (;;) {
				// Recibimos paquete
				DatagramPacket requestPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
				serverSocket.receive(requestPacket);

				String receivedName = new String(requestPacket.getData(), 0, requestPacket.getLength());
				System.out.println("Consulta recibida: " + receivedName);

				// Buscar el nombre en las propiedades
				String response = dnsProperties.getProperty(receivedName, "El nombre no se encuentra");

				// Preparamos y enviamos la respuesta
				byte[] responseBytes = response.getBytes();
				InetAddress clientAddress = requestPacket.getAddress();
				int clientPort = requestPacket.getPort();

				DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress,
						clientPort);
				serverSocket.send(responsePacket);

				System.out.println("Respuesta enviada: " + response);
			}
		} catch (IOException e) {
			System.err.println("Error en el servidor DNS: " + e.getMessage());
		}
	}
}
