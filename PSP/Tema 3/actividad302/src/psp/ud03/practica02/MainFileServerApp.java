package psp.ud03.practica02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFileServerApp {
	
	private int port;
	
	public MainFileServerApp(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		// Verificamos los parámetros
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 4321;
		MainFileServerApp app = new MainFileServerApp(port);
		app.run();
	}

	private void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto: " + port);

            for(;;) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());

                // Manejar cada cliente en un hilo separado
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }

	} 

}
