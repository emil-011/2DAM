package psp.ud03.practica01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DNSClientApp {
	private static final int SERVER_PORT = 2222;

	public static void main(String[] args) {
		DNSClientApp app = new DNSClientApp();
		app.run();
	}

	private  void run() {
		try (DatagramSocket clientSocket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {

			InetAddress serverAddress = InetAddress.getByName("localhost");
			String message;

			do {
				// Pedimos
				System.out.print("Introduce el nombre a consultar (vacío para salir): ");
				message = scanner.nextLine();

				if (message.length() > 0) {

					// Enviar consulta al servidor
					byte[] requestBytes = message.getBytes();
					DatagramPacket requestPacket = new DatagramPacket(requestBytes, requestBytes.length, serverAddress,
							SERVER_PORT);
					clientSocket.send(requestPacket);

					// Recibir respuesta del servidor
					byte[] responseBuffer = new byte[1024];
					DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
					clientSocket.receive(responsePacket);

					String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
					System.out.println("Respuesta del servidor: " + response);
				}
			} while (message.length() > 0);

		} catch (IOException e) {
			System.err.println("Error en el cliente DNS: " + e.getMessage());
		}
	}
}
